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
import repchain.inter.cooperation.middleware.model.AckObj;
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
import repchain.inter.cooperation.middleware.proto.ResultFile;
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
    private static final String DOWNLOAD = "/download";

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
                .addAction("/download", this::parentMsg)
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
            MsgVo msgVo;
            if (MSG.equals(req.getPath())) {
                msgVo = (MsgVo) msg(id, seq, endFlag, url, bReqFlag, method, callbackMethod, callbackUrl, cid, sync, map);
            } else if (FILE.equals(req.getPath())) {
                String filepath = req.getParam("filepath");
                String fileHash = req.getParam("fileHash");
                if (sync) {
                    msgVo = (MsgVo) file(id, seq, endFlag, url, bReqFlag, method,
                            callbackMethod, callbackUrl, cid, true, filepath, fileHash, map);
                } else {
                    msgVo = (MsgVo) fileAsync(id, seq, endFlag, url, bReqFlag, method, callbackMethod,
                            callbackUrl, cid, false, filepath, fileHash, map, res);
                }
            } else {
                msgVo = downloadFile(id, seq, endFlag, url, bReqFlag, method,
                        callbackMethod, callbackUrl, cid, true, map);
            }
            Result result = msgVo.getResult();
            InterCoResult coResult;
            if (result != null) {
                if (result.getCode() == 0) {
                    coResult = InterCoResult.builder().cid(msgVo.getReqAckProof().getCid()).code(0).msg("Action OK").data(result.getData()).build();
                } else {
                    coResult = InterCoResult.builder().code(result.getCode()).msg(result.getMsg()).build();
                }
            } else {
                ResultFile resultFile = msgVo.getResultFile();
                if (resultFile.getCode() == 0) {
                    coResult = InterCoResult.builder().cid(msgVo.getReqAckProof().getCid()).code(0)
                            .msg("Action OK").data(resultFile.getData()).filePath(resultFile.getFilepath()).build();
                } else {
                    coResult = InterCoResult.builder().code(resultFile.getCode()).msg(resultFile.getMsg()).build();
                }
            }
            if (!(sync && FILE.equals(req.getPath()))) {
                res.write(JSONUtil.toJsonStr(coResult));
                if (msgVo.getReqAckProof() != null) {
                    if (commit != null) {
                        commit.saveProof(msgVo.getReqAckProof());
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            res.write(JSONUtil.toJsonStr(InterCoResult.builder().code(2).msg(e.getMessage()).build()));
        }
    }

    @Override
    public Object msg(String serviceId, int seq, boolean isEnd,
                      String url, boolean bReqFlag, String method,
                      String callbackMethod, String callbackUrl, String cid,
                      boolean sync, Map<String, Object> map) {
        String data = "";
        if (map != null && !map.isEmpty()) {
            data = JSONUtil.toJsonStr(map);
        }
        if (StrUtil.isBlank(cid)) {
            if (!bReqFlag) {
                throw new ServiceException("应答请求参数必须携带cid，cid不能为空！！");
            }
            // 创建交易id
            cid = SnowIdGenerator.getId();
        }
        AckObj ackObj = getAckObj(serviceId, bReqFlag, cid);
        // 对业务请求数据进行hash取值
        String contentHash = DigestUtil.sha256Hex(data);
        Signature signature = getSign(contentHash, ackObj);
        Header header = getHeader(ackObj, cid, method, url, bReqFlag, isEnd, seq, callbackUrl, callbackMethod, data, sync, signature);
        String host = bReqFlag ? ackObj.getTo().getAddr() : ackObj.getFrom().getAddr();
        int port = bReqFlag ? ackObj.getTo().getPort() : ackObj.getFrom().getPort();
        TransEntity transEntity = TransEntity.newBuilder().setHeader(JSONUtil.toJsonStr(header)).setHost(host).setPort(port).build();
        Result result = communicationClient.sendMessage(transEntity);
        ReqAckProof rb = commit != null ? TransTools.getReqAckProof(header, contentHash, signature,
                JSONUtil.toBean(result.getSignature(), Signature.class)) : null;
        return MsgVo.builder().reqAckProof(rb).result(result).build();
    }

    @Override
    public Object file(String serviceId, int seq, boolean isEnd,
                       String url, boolean bReqFlag, String method,
                       String callbackMethod, String callbackUrl, String cid,
                       boolean sync, String filePath, String fileHash, Map<String, Object> map) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ServiceException("文件不存在，请检查文件路径是否正确！");
        }
        String data = "";
        if (map != null && !map.isEmpty()) {
            data = JSONUtil.toJsonStr(map);
        }
        if (StrUtil.isBlank(cid)) {
            if (!bReqFlag) {
                throw new ServiceException("应答请求参数必须携带cid，cid不能为空！！");
            }
            // 创建交易id
            cid = SnowIdGenerator.getId();
        }
        AckObj ackObj = getAckObj(serviceId, bReqFlag, cid);
        Signature signature = getSign(fileHash, ackObj);
        // 创建请求头
        Header header = getHeader(ackObj, cid, method, url, bReqFlag, isEnd, seq, callbackUrl,
                callbackMethod, data, sync, signature);
        String host = bReqFlag ? ackObj.getTo().getAddr() : ackObj.getFrom().getAddr();
        int port = bReqFlag ? ackObj.getTo().getPort() : ackObj.getFrom().getPort();
        TransFile transFile = TransFile.newBuilder().setSha256(fileHash)
                .setFileName(file.getName()).setPort(port).setHost(host).setHeader(JSONUtil.toJsonStr(header)).build();
        Result result = communicationClient.sendFile(transFile, file);
        ReqAckProof rb = commit != null ?
                TransTools.getReqAckProof(header, fileHash, signature, JSONUtil.toBean(result.getSignature(), Signature.class))
                : null;
        return MsgVo.builder().reqAckProof(rb).result(result).build();
    }

    public Object fileAsync(String serviceId, int seq, boolean isEnd,
                            String url, boolean bReqFlag, String method,
                            String callbackMethod, String callbackUrl, String cid,
                            boolean sync, String filePath, String fileHash, Map<String, Object> map, HttpServerResponse res) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ServiceException("文件不存在，请检查文件路径是否正确！");
        }
        String data = "";
        if (map != null && !map.isEmpty()) {
            data = JSONUtil.toJsonStr(map);
        }
        if (StrUtil.isBlank(cid)) {
            if (!bReqFlag) {
                throw new ServiceException("应答请求参数必须携带cid，cid不能为空！！");
            }
            // 创建交易id
            cid = SnowIdGenerator.getId();
        }
        AckObj ackObj = getAckObj(serviceId, bReqFlag, cid);
        Signature signature = getSign(fileHash, ackObj);
        // 创建请求头
        Header header = getHeader(ackObj, cid, method, url, bReqFlag, isEnd, seq, callbackUrl, callbackMethod, data, sync, signature);
        String host = bReqFlag ? ackObj.getTo().getAddr() : ackObj.getFrom().getAddr();
        int port = bReqFlag ? ackObj.getTo().getPort() : ackObj.getFrom().getPort();
        TransFile beginTrans = TransFile.newBuilder().setSha256(fileHash).setBegin(true)
                .setFileName(file.getName()).setPort(port).setHost(host).setHeader(JSONUtil.toJsonStr(header)).build();
        Result result = communicationClient.sendFile(beginTrans, file);
        ReqAckProof rb=TransTools.getReqAckProof(header, fileHash, signature, JSONUtil.toBean(result.getSignature(), Signature.class));
        if (commit != null) {
            commit.saveProof(rb);
        }
        InterCoResult coResult;
        if (result.getCode() == 0) {
            coResult = InterCoResult.builder().cid(rb.getCid()).code(0).msg("Action OK").data(result.getData()).build();
        } else {
            coResult = InterCoResult.builder().code(result.getCode()).msg(result.getMsg()).build();
        }
        res.write(JSONUtil.toJsonStr(coResult));
        if (result.getCode() == 0) {
            TransFile transFile = TransFile.newBuilder().setSha256(fileHash)
                    .setFileName(file.getName()).setPort(port).setHost(host).setHeader(JSONUtil.toJsonStr(header)).build();
            result = communicationClient.sendFile(transFile, file);
            rb = result.getCode() == 0 ?
                    TransTools.getReqAckProof(header, fileHash, signature, JSONUtil.toBean(result.getSignature(), Signature.class))
                    : null;
        }
        return MsgVo.builder().reqAckProof(rb).result(result).build();
    }

    public MsgVo downloadFile(String serviceId, int seq, boolean isEnd, String url, boolean bReqFlag,
                              String method, String callbackMethod, String callbackUrl, String cid,
                              boolean sync, Map<String, Object> map) {
        String data = "";
        if (map != null && !map.isEmpty()) {
            data = JSONUtil.toJsonStr(map);
        }
        if (StrUtil.isBlank(cid)) {
            if (!bReqFlag) {
                throw new ServiceException("应答请求参数必须携带cid，cid不能为空！！");
            }
            // 创建交易id
            cid = SnowIdGenerator.getId();
        }
        AckObj ackObj = getAckObj(serviceId, bReqFlag, cid);
        // 对业务请求数据进行hash取值
        String contentHash = DigestUtil.sha256Hex(data);
        Signature signature = getSign(contentHash, ackObj);
        Header header = getHeader(ackObj, cid, method, url, bReqFlag, isEnd, seq, callbackUrl, callbackMethod, data, sync, signature);
        String host = bReqFlag ? ackObj.getTo().getAddr() : ackObj.getFrom().getAddr();
        int port = bReqFlag ? ackObj.getTo().getPort() : ackObj.getFrom().getPort();
        TransEntity transEntity = TransEntity.newBuilder().setHeader(JSONUtil.toJsonStr(header)).setHost(host).setPort(port).build();
        ResultFile result = communicationClient.downloadFile(transEntity);
        ReqAckProof rb = commit != null ? TransTools.getReqAckProof(header, contentHash, signature,
                JSONUtil.toBean(result.getSignature(), Signature.class)) : null;
        return MsgVo.builder().reqAckProof(rb).resultFile(result).build();
    }

    private void testFile(HttpServerRequest httpServerRequest, HttpServerResponse httpServerResponse) {
        InputStream in = httpServerRequest.getBodyStream();
        int size;
        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fos = new FileOutputStream("/Volumes/DATA/test");
            while ((size = in.read(buffer, 0, 1024)) != -1) {
                fos.write(buffer, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpServerResponse.write("ok");
    }

    private AckObj getAckObj(String serviceId, boolean bReq, String cid) {
        ApiServAndAck to;
        ApiServAndAck from;
        ApiDefinition apiDefinition;
        Header header = null;
        if (bReq) {
            Service service = YamlUtils.getService(serviceId);
            to = MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, service.getE_to(), ApiServAndAck.class);
            from = MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, service.getE_from(), ApiServAndAck.class);
        } else {
            header = MyCacheManager.getValue(EhCacheConstant.ASYNC_HEADER, cid, Header.class);
            if (header == null) {
                throw new ServiceException("无法从缓存中获取请求头信息，缓存id:" + cid);
            }
            to = MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, header.getE_to(), ApiServAndAck.class);
            from = MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, header.getE_from(), ApiServAndAck.class);
        }
        if (to == null || from == null) {
            throw new ServiceException("无法从区块链获取登记信息，请确认信息是否提交或稍后重试");
        }
        if (!to.getD_id().equals(from.getD_id())) {
            throw new ServiceException("服务登记和服务调用所实现的接口定义不一致！");
        }
        apiDefinition = MyCacheManager.getValue(EhCacheConstant.API_DEFINITION, to.getD_id(), ApiDefinition.class);
        if (!to.getD_id().equals(from.getD_id())) {
            throw new ServiceException("无法从区块链获取服务定义信息，请确认信息是否提交或稍后重试");
        }
        return AckObj.builder().to(to).from(from).apiDefinition(apiDefinition).header(header).build();
    }

    private Signature getSign(String hash, AckObj ackObj) {
        RepChain repchain = YamlUtils.getRepchain();
        PrivateKey privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
        return TransTools.getSignature(privateKey, hash, repchain.getCreditCode(), repchain.getCertName(), ackObj.getApiDefinition().getAlgo_sign());
    }

    private Header getHeader(AckObj ackObj, String cid, String method, String url, boolean bReqFlag,
                             boolean isEnd, int seq, String callbackUrl, String callbackMethod,
                             String data, boolean sync, Signature signature) {
        // 创建请求头
        return Header.builder()
                .cid(cid)
                .e_from(ackObj.getFrom().getId())
                .e_to(ackObj.getTo().getId())
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
    }
}
