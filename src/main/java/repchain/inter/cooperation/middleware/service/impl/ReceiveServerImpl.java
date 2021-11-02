package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.constant.MiddleConstant;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.model.MsgVo;
import repchain.inter.cooperation.middleware.model.SysCert;
import repchain.inter.cooperation.middleware.model.tran.ApiDefinition;
import repchain.inter.cooperation.middleware.model.tran.ApiServAndAck;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;
import repchain.inter.cooperation.middleware.model.tran.Signature;
import repchain.inter.cooperation.middleware.model.yml.MiddleServer;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.model.yml.Service;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.service.CommunicationClient;
import repchain.inter.cooperation.middleware.service.ReceiveServer;
import repchain.inter.cooperation.middleware.service.TransactionCommit;
import repchain.inter.cooperation.middleware.utils.*;

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

    private static final Logger logger = LoggerFactory.getLogger(CommunicationServerImpl.class);

    public CommunicationClient communicationClient;

    public TransactionCommit commit;

    @Override
    public void setCommunicationClient(CommunicationClient communicationClient) {
        this.communicationClient = communicationClient;
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
                .addAction("/file", this::file)
                .start();
        logger.info("Http Server started, listening on " + port);
    }

    public void parentMsg(HttpServerRequest req, HttpServerResponse res) {
        try {
            String id = req.getParam("serviceId");
            int seq = Integer.parseInt(req.getParam("seq"));
            Integer isEnd = Integer.parseInt(req.getParam("isEnd"));
            Integer bReq = Integer.parseInt(req.getParam("bReq"));
            String url = req.getParam("url");
            String method = req.getParam("method");
            String callbackMethod = req.getParam("callbackMethod");
            String callbackUrl = req.getParam("callbackUrl");
            String cid = req.getParam("cid");
            boolean bReqFlag;
            if (bReq == MiddleConstant.REQUEST) {
                bReqFlag = true;
            } else {
                bReqFlag = false;
            }
            boolean endFlag;
            if (isEnd == MiddleConstant.NOT_END) {
                endFlag = false;
            } else {
                endFlag = true;
            }
            String data = req.getParam("data");
            Map<String, Object> map = JSONUtil.parseObj(data);
            res.setContentType("text/html;charset=utf-8");
            MsgVo msgVo = (MsgVo) msg(id, seq, endFlag, url, bReqFlag, method, callbackMethod, callbackUrl,cid, map);
            Result result = msgVo.getResult();
            res.write(result.getData());
            ReqAckProof rb = msgVo.getReqAckProof();
            EhcacheManager.put(EhCacheConstant.REQ_ACK_PROOF, rb.getCid() + rb.getHash() + rb.getTm_create(), rb);
            // 创建请求实例，若用Spring 此处可以创建javabean
            RepChain repchain = YamlUtils.getRepchain();
            RequestAck requestAck = new RequestAck(repchain.getHost());
            SysCert sysCert = PkUtil.getSysCert(repchain);
            JSONObject jsonObject = requestAck.rb(rb, sysCert);
            // 若果有错误信息，则提交存证数据失败
            if (!StrUtil.isBlankIfStr(jsonObject.get("err"))) {
                System.out.println("提交区块链数据失败：" + jsonObject);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            res.write(e.getMessage());
        }
    }

    @Override
    public Object msg(String serviceId, int seq, boolean isEnd,
                      String url, boolean bReqFlag, String method,
                      String callbackMethod, String callbackUrl, String cid, Map<String, Object> map) {
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
        String data = JSONUtil.toJsonStr(map);
        if (StrUtil.isNotEmpty(cid)) {
            // 创建交易id
            cid = SnowIdGenerator.getId();
        }
        RepChain repchain = YamlUtils.getRepchain();
        PrivateKey privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
        // 对业务请求数据进行hash取值
        String contentHash = DigestUtil.sha256Hex(JSONUtil.toJsonStr(data));
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
                .validStr(signature.getHash())
                .signData(signature.getSign())
                .data(data)
                .httpType(method)
                .url(url)
                .build();
        TransEntity transEntity = TransEntity.newBuilder().setHeader(JSONUtil.toJsonStr(header)).setHost(to.getAddr()).setPort(to.getPort()).build();
        Result result = communicationClient.sendMessage(transEntity);
        ReqAckProof rb = TransTools.getReqAckProof(header, contentHash, signature, JSONUtil.toBean(result.getSignature(), Signature.class));
        return MsgVo.builder().reqAckProof(rb).result(result).build();
    }

    @Override
    public void file(HttpServerRequest request, HttpServerResponse response) {
        String resultObj;
        try {
            String data = request.getParam("data");
            TransEntity transEntity = TransEntity.newBuilder().setHeader("").build();
            Result result = communicationClient.sendMessage(transEntity);
            System.out.println(result.getData());
            resultObj = result.getData();
            response.setContentType("text/html;charset=utf-8");
            response.write(result.getData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultObj = "error";
        }
        response.setContentType("text/html;charset=utf-8");
        response.write(resultObj);
    }

    @Override
    public void setTransactionCommit(TransactionCommit commit) {
        this.commit = commit;
    }
}
