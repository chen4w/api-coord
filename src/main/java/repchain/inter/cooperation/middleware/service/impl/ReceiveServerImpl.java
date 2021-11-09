package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.client.ReqOption;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.constant.MiddleConstant;
import repchain.inter.cooperation.middleware.exception.ServiceException;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.model.InterCoResult;
import repchain.inter.cooperation.middleware.model.MsgVo;
import repchain.inter.cooperation.middleware.model.tran.ApiDefinition;
import repchain.inter.cooperation.middleware.model.tran.ApiServAndAck;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;
import repchain.inter.cooperation.middleware.model.tran.Signature;
import repchain.inter.cooperation.middleware.model.yml.MiddleServer;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.model.yml.Service;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.proto.TransFile;
import repchain.inter.cooperation.middleware.service.CommunicationClient;
import repchain.inter.cooperation.middleware.service.Persistence;
import repchain.inter.cooperation.middleware.service.ReceiveServer;
import repchain.inter.cooperation.middleware.service.TransactionCommit;
import repchain.inter.cooperation.middleware.utils.*;

import java.io.*;
import java.security.PrivateKey;
import java.util.Map;
import java.util.concurrent.*;


/**
 * @author lhc
 * @version 1.0
 * @className ReceiveServerImpl
 * @date 2021年10月27日 9:53 上午
 * @description 描述
 */
public class ReceiveServerImpl implements ReceiveServer {

    private static final String MSG = "/msg";
    private static final String FILE = "/file";

    private static final Logger logger = LoggerFactory.getLogger(ReceiveServerImpl.class);

    public CommunicationClient communicationClient;

    public TransactionCommit commit;

    public Persistence persistence;

    @Override
    public void setCommunicationClient(CommunicationClient communicationClient) {
        this.communicationClient = communicationClient;
    }

    @Override
    public void setTransactionCommit(TransactionCommit commit) {
        this.commit = commit;
    }

    @Override
    public void setPersistence(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public void start() {
        MiddleServer recServer = YamlUtils.middleConfig.getMiddleware().getRecServer();
        int port = recServer.getPort();
        // 创建线程池
        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(recServer.getCorePoolSize())
                .setMaxPoolSize(recServer.getMaxPoolSize())
                .setWorkQueue(new LinkedBlockingQueue<>(recServer.getWorkQueue()))
                .build();
        // 创建http服务
        HttpUtil.createServer(port)
                .setExecutor(executor)
                .addAction("/msg", this::parentMsg)
                .addAction("/file", this::parentMsg)
                .addAction("/testFile", this::testFile)
                .start();
        logger.info("Http Server started, listening on " + port);
    }


    public void parentMsg(HttpServerRequest req, HttpServerResponse res) {
        try {
            // 获取请求参数
            String id = req.getParam("serviceId");
            int seq = Integer.parseInt(req.getParam("seq"));
            int isEnd = Integer.parseInt(req.getParam("isEnd"));
            int bReq = Integer.parseInt(req.getParam("bReq"));
            int isSync = Integer.parseInt(req.getParam("sync"));
            String url = req.getParam("url");
            String method = req.getParam("method");
            String callbackMethod = req.getParam("callbackMethod");
            String callbackUrl = req.getParam("callbackUrl");
            String cid = req.getParam("cid");
            boolean bReqFlag = bReq == MiddleConstant.REQUEST;
            boolean endFlag = isEnd != MiddleConstant.NOT_END;
            boolean sync = isSync == ReqOption.TRUE;
            String data = req.getParam("data");
            Map<String, Object> map = JSONUtil.parseObj(data);
            res.setContentType("text/html;charset=utf-8");
            // 获取根据yml文件及区块数据，获取接口定义，接口登记，及接口调用信息
            Service service = YamlUtils.getService(id);
            ApiServAndAck to = (ApiServAndAck) EhcacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, service.getE_to());
            ApiServAndAck from = (ApiServAndAck) EhcacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, service.getE_from());
            if (to == null || from == null) {
                throw new ServiceException("无法从区块链获取登记信息，请确认信息是否提交或稍后重试");
            }
            if (!to.getD_id().equals(from.getD_id())) {
                throw new ServiceException("服务登记和服务调用所实现的接口定义不一致！");
            }
            ApiDefinition apiDefinition = (ApiDefinition) EhcacheManager.getValue(EhCacheConstant.API_DEFINITION, to.getD_id());
            if (!to.getD_id().equals(from.getD_id())) {
                throw new ServiceException("无法从区块链获取服务定义信息，请确认信息是否提交或稍后重试");
            }
            MsgVo msgVo;
            if (MSG.equals(req.getPath())) {
                msgVo = (MsgVo) msg(to,from,apiDefinition, seq, endFlag, url, bReqFlag, method, callbackMethod, callbackUrl, cid, sync, map);
            } else {
                String filepath = req.getParam("filepath");
                msgVo = (MsgVo) file(id, seq, endFlag, url, bReqFlag, method, callbackMethod, callbackUrl, cid, sync, filepath, map);
            }

            Result result = msgVo.getResult();
            InterCoResult coResult;
            if (result.getCode() == 0) {
                coResult = InterCoResult.builder().cid(msgVo.getReqAckProof().getCid()).code(0).msg("Action OK").data(result.getData()).build();
            } else {
                coResult = InterCoResult.builder().code(result.getCode()).msg("Action OK").msg(result.getMsg()).build();
            }
            res.write(JSONUtil.toJsonStr(coResult));
            if (msgVo.getReqAckProof() != null) {
                commit.saveProof(msgVo.getReqAckProof());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            res.write(JSONUtil.toJsonStr(InterCoResult.builder().code(2).msg(e.getMessage()).build()));
        }
    }

    @Override
    public Object msg(ApiServAndAck to,ApiServAndAck from,ApiDefinition apiDefinition, int seq, boolean isEnd,
                      String url, boolean bReqFlag, String method,
                      String callbackMethod, String callbackUrl, String cid,
                      boolean sync, Map<String, Object> map) {

        String data = "";
        if (map != null && map.isEmpty()) {
            data = JSONUtil.toJsonStr(map);
        }
        if (StrUtil.isBlank(cid)) {
            // 创建交易id
            cid = SnowIdGenerator.getId();
        }
        RepChain repchain = YamlUtils.getRepchain();
        PrivateKey privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
        // 对业务请求数据进行hash取值
        String contentHash = DigestUtil.sha256Hex(data);
        Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(), repchain.getCertName(), apiDefinition.getAlgo_sign());
        // 创建请求头
        Header header = Header.builder()
                .cid(cid)
                .e_from(from.getId())
                .e_to(to.getId())
                .method(method + " " + url)
                .b_req(bReqFlag)
                .b_end(isEnd)
                .seq(seq)
                .tm_create(System.currentTimeMillis())
                .callback_url(callbackUrl)
                .callback_method(callbackMethod)
                .validStr(signature.getSign())
                .signData(signature.getHash())
                .data(data)
                .httpType(method)
                .url(url)
                .sync(sync)
                .build();
        String host;
        int port;
        if (bReqFlag) {
            host = to.getAddr();
            port = to.getPort();
        } else {
            host = from.getAddr();
            port = from.getPort();
        }
        TransEntity transEntity = TransEntity.newBuilder().setHeader(JSONUtil.toJsonStr(header)).setHost(host).setPort(port).build();
        Result result = communicationClient.sendMessage(transEntity);
        ReqAckProof rb;
        if (result.getCode() == 0) {
            rb = TransTools.getReqAckProof(header, contentHash, signature, JSONUtil.toBean(result.getSignature(), Signature.class));
        } else {
            rb = null;
        }
        return MsgVo.builder().reqAckProof(rb).result(result).build();
    }

    @Override
    public Object file(String serviceId, int seq, boolean isEnd,
                       String url, boolean bReqFlag, String method,
                       String callbackMethod, String callbackUrl, String cid,
                       boolean sync, String filePath, Map<String, Object> map) {
        Service service = YamlUtils.getService(serviceId);
        ApiServAndAck to = (ApiServAndAck) EhcacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, service.getE_to());
        ApiServAndAck from = (ApiServAndAck) EhcacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, service.getE_from());
        if (to == null || from == null) {
            return Result.newBuilder().setCode(2).setMsg("无法从区块链获取登记信息，请确认信息是否提交或稍后重试").build();
        }
        if (!to.getD_id().equals(from.getD_id())) {
            return Result.newBuilder().setCode(2).setMsg("服务登记和服务调用所实现的接口定义不一致！").build();
        }
        ApiDefinition apiDefinition = (ApiDefinition) EhcacheManager.getValue(EhCacheConstant.API_DEFINITION, to.getD_id());
        if (!to.getD_id().equals(from.getD_id())) {
            return Result.newBuilder().setCode(2).setMsg("无法从区块链获取服务定义信息，请确认信息是否提交或稍后重试").build();
        }
//        ComClientSingle clientSingle = new ComClientSingle("localhost", 8080);
        TransFile transFile = TransFile.newBuilder().setFileName("test").build();
//        try {
//        File file = new File("");
        Result result = communicationClient.sendFile(transFile, new File(filePath));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        response.setContentType("text/html;charset=utf-8");
//        try {
//            response.write(JsonFormat.printer().print(result));
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    private void testFile(HttpServerRequest httpServerRequest, HttpServerResponse httpServerResponse) {
        System.out.println("get");
//        String fileName = httpServerRequest.getParam("fileName");
//        try {
        InputStream in = httpServerRequest.getBodyStream();
        int size = 0;
        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fos = new FileOutputStream("/Volumes/DATA/test");
            while ((size = in.read(buffer, 0, 1024)) != -1) {
                fos.write(buffer, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        httpServerResponse.write("ok");
    }
}
