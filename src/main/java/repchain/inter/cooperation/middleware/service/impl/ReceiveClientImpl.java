package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.model.tran.ApiDefinition;
import repchain.inter.cooperation.middleware.model.tran.ApiServAndAck;
import repchain.inter.cooperation.middleware.model.tran.Signature;
import repchain.inter.cooperation.middleware.model.yml.RecClient;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.proto.Result;
import repchain.inter.cooperation.middleware.proto.ResultFile;
import repchain.inter.cooperation.middleware.proto.TransEntity;
import repchain.inter.cooperation.middleware.service.AuthFilter;
import repchain.inter.cooperation.middleware.service.ReceiveClient;
import repchain.inter.cooperation.middleware.utils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className ReceiveClientImpl
 * @date 2021年10月27日 9:53 上午
 * @description 描述
 */
public class ReceiveClientImpl implements ReceiveClient {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveClientImpl.class);

    public AuthFilter authFilter;

    @Override
    public void setAuthFilter(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void download(TransEntity request, Object response) {
        RepChain repchain = YamlUtils.getRepchain();
        Header header = JSONUtil.toBean(request.getHeader(), Header.class);
        StreamObserver<ResultFile> stream = (StreamObserver<ResultFile>) response;
        ApiServAndAck from = MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, header.getE_from(), ApiServAndAck.class);
        PrivateKey privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
        try {
            if (from == null) {
                String msg = "无法在区块链找到对应的接口登记或接口调用";
                // 对业务请求数据进行hash取值
                String contentHash = DigestUtil.sha256Hex(msg);
                Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(), repchain.getCertName(), "sha256withecdsa");
                stream.onNext(ResultFile.newBuilder().setCode(1).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).setBegin(true).build());
            } else {
                ApiDefinition apiDefinition = MyCacheManager.getValue(EhCacheConstant.API_DEFINITION, from.getD_id(), ApiDefinition.class);
                if (authFilter.validAuth(header, apiDefinition.getAlgo_sign(), header.getB_req())) {
                    Map<String, Object> map;
                    if (header.getData() != null && header.getData().contains("{")) {
                        map = JSONUtil.parseObj(header.getData());
                    } else {
                        map = new HashMap<>(1);
                    }
                    RecClient recClient = YamlUtils.middleConfig.getMiddleware().getRecClient();
                    String subUrl;
                    if (header.getUrl().startsWith("/")) {
                        subUrl = header.getUrl();
                    } else {
                        subUrl = "/" + header.getUrl();
                    }
                    Map<String, String> httpHeaders = new HashMap<>();
                    if (request.getHttpHeader().contains("{")) {
                        Map<String, Object> httpHeaderMap = JSONUtil.parseObj(request.getHttpHeader());
                        for (Map.Entry<String, Object> entry : httpHeaderMap.entrySet()) {
                            httpHeaders.put(entry.getKey(), String.valueOf(entry.getValue()));
                        }
                    } else {
                        httpHeaders = new HashMap<>(1);
                    }
                    String url = recClient.getProtocol() + "://" + recClient.getHost() + ":" + recClient.getPort() + subUrl;
                    String type = header.getHttpType();
                    HttpResponse httpresponse = null;
                    if ("GET".equals(type)) {
                        httpresponse = HttpRequest.get(url)
                                .form(map)
                                .headerMap(httpHeaders,true)
                                .timeout(recClient.getTimeout())
                                .execute();
                    }
                    if ("POST".equals(type)) {
                        httpresponse = HttpRequest.post(url)
                                .form(map)
                                .headerMap(httpHeaders,true)
                                .timeout(recClient.getTimeout())
                                .execute();
                    }
                    if ("PUT".equals(type)) {
                        httpresponse = HttpRequest.put(url)
                                .form(map)
                                .headerMap(httpHeaders,true)
                                .timeout(recClient.getTimeout())
                                .execute();
                    }
                    if ("PATCH".equals(type)) {
                        httpresponse = HttpRequest.patch(url)
                                .form(map)
                                .timeout(recClient.getTimeout())
                                .headerMap(httpHeaders,true)
                                .execute();
                    }
                    if ("DELETE".equals(type)) {
                        httpresponse = HttpRequest.delete(url)
                                .form(map)
                                .timeout(recClient.getTimeout())
                                .headerMap(httpHeaders,true)
                                .execute();
                    }
                    String filePath = httpresponse.header("filePath");
                    if (filePath == null) {
                        String msg = "无法从服务方获取文件";
                        // 对业务请求数据进行hash取值
                        String contentHash = DigestUtil.sha256Hex(msg);
                        Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(),
                                repchain.getCertName(), "sha256withecdsa");
                        stream.onNext(ResultFile.newBuilder().setCode(1).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).setBegin(true).build());
                    }
                    String data = httpresponse.body();
                    FileInputStream is = new FileInputStream(filePath);
                    File downloadFile = new File(filePath);
                    String hash = GetFileSHA256.getFileSha256(downloadFile);
                    Signature signature = TransTools.getSignature(privateKey, hash, repchain.getCreditCode(),
                            repchain.getCertName(), apiDefinition.getAlgo_sign());
                    ResultFile resultFile = ResultFile.newBuilder().setFileName(downloadFile.getName()).setFilepath(filePath).setSignature(JSONUtil.toJsonStr(signature)).setSha256(hash).setData(data).setBegin(true).build();
                    stream.onNext(resultFile);
                    byte[] buff = new byte[2048];
                    int len;
                    while ((len = is.read(buff)) != -1) {
                        stream.onNext(resultFile.toBuilder().setBegin(false).setFile(ByteString.copyFrom(buff, 0, len)).build());
                    }
                    is.close();
                } else {
                    String msg = "无权限";
                    // 对业务请求数据进行hash取值
                    String contentHash = DigestUtil.sha256Hex(msg);
                    Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(),
                            repchain.getCertName(), "sha256withecdsa");
                    stream.onNext(ResultFile.newBuilder().setCode(3).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).setBegin(true).build());
                }
            }
            stream.onCompleted();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            String msg = "远程中间件错误：" + e.getMessage();
            // 对业务请求数据进行hash取值
            String contentHash = DigestUtil.sha256Hex(msg);
            Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(), repchain.getCertName(), "sha256withecdsa");
            stream.onNext(ResultFile.newBuilder().setCode(2).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).setBegin(true).build());
            stream.onCompleted();
        }
    }

    @Override
    public Result msg(TransEntity transEntity) {
        RepChain repchain = YamlUtils.getRepchain();
        Header header = JSONUtil.toBean(transEntity.getHeader(), Header.class);
        ApiServAndAck from = MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, header.getE_from(), ApiServAndAck.class);
        PrivateKey privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
        if (from == null) {
            String msg = "无法在区块链找到对应的接口登记或接口调用";
            // 对业务请求数据进行hash取值
            String contentHash = DigestUtil.sha256Hex(msg);
            Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(), repchain.getCertName(), "sha256withecdsa");
            return Result.newBuilder().setCode(1).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).build();
        } else {
            ApiDefinition apiDefinition = MyCacheManager.getValue(EhCacheConstant.API_DEFINITION, from.getD_id(), ApiDefinition.class);
            if (authFilter.validAuth(header, apiDefinition.getAlgo_sign(), header.getB_req())) {
                if (!header.getSync()) {
                    MyCacheManager.put(EhCacheConstant.ASYNC_HEADER, header.getCid(), header);
                }
                Map<String, Object> map;
                if (header.getData() != null && header.getData().contains("{")) {
                    map = JSONUtil.parseObj(header.getData());
                } else {
                    map = new HashMap<>(1);
                }
                RecClient recClient = YamlUtils.middleConfig.getMiddleware().getRecClient();
                String subUrl;
                if (header.getUrl().startsWith("/")) {
                    subUrl = header.getUrl();
                } else {
                    subUrl = "/" + header.getUrl();
                }
                String url = recClient.getProtocol() + "://" + recClient.getHost() + ":" + recClient.getPort() + subUrl;

                String type = header.getHttpType();
                String result = "";
                Map<String, String> httpHeaders = new HashMap<>();
                if (transEntity.getHttpHeader().contains("{")) {
                    Map<String, Object> httpHeaderMap = JSONUtil.parseObj(transEntity.getHttpHeader());
                    for (Map.Entry<String, Object> entry : httpHeaderMap.entrySet()) {
                        httpHeaders.put(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                } else {
                    httpHeaders = new HashMap<>(1);
                }
                if ("GET".equals(type)) {
                    result = HttpRequest.get(url)
                            .form(map)
                            .headerMap(httpHeaders,true)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("POST".equals(type)) {
                    result = HttpRequest.post(url)
                            .form(map)
                            .headerMap(httpHeaders,true)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("PUT".equals(type)) {
                    result = HttpRequest.put(url)
                            .form(map)
                            .headerMap(httpHeaders,true)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("PATCH".equals(type)) {
                    result = HttpRequest.patch(url)
                            .form(map)
                            .headerMap(httpHeaders,true)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("DELETE".equals(type)) {
                    result = HttpRequest.delete(url)
                            .form(map)
                            .headerMap(httpHeaders,true)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }

                String contentHash = DigestUtil.sha256Hex(result);
                Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(),
                        repchain.getCertName(), apiDefinition.getAlgo_sign());
                return Result.newBuilder().setData(result).setMsg("Action OK").setSignature(JSONUtil.toJsonStr(signature)).build();
            } else {
                String msg = "无权限";
                // 对业务请求数据进行hash取值
                String contentHash = DigestUtil.sha256Hex(msg);
                Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(),
                        repchain.getCertName(), "sha256withecdsa");
                return Result.newBuilder().setCode(3).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).build();
            }
        }
    }
}
