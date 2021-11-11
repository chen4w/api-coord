package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
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
import repchain.inter.cooperation.middleware.utils.MyCacheManager;
import repchain.inter.cooperation.middleware.utils.PkUtil;
import repchain.inter.cooperation.middleware.utils.TransTools;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

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

    public AuthFilter authFilter;

    @Override
    public void setAuthFilter(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Override
    public ResultFile download(TransEntity request) {
        return null;
    }

    @Override
    public Result msg(TransEntity transEntity) {
        RepChain repchain = YamlUtils.getRepchain();
        Header header = JSONUtil.toBean(transEntity.getHeader(), Header.class);
        ApiServAndAck from =  MyCacheManager.getValue(EhCacheConstant.API_SERV_AND_ACK, header.getE_from(),ApiServAndAck.class);
        PrivateKey privateKey = PkUtil.getPrivateKey(repchain.handlePrivateKey(), repchain.getPassword());
        if (from == null) {
            String msg = "无法在区块链找到对应的接口登记或接口调用";
            // 对业务请求数据进行hash取值
            String contentHash = DigestUtil.sha256Hex(msg);
            Signature signature = TransTools.getSignature(privateKey, contentHash, repchain.getCreditCode(), repchain.getCertName(), "sha256withecdsa");
            return Result.newBuilder().setCode(1).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).build();
        } else {
            ApiDefinition apiDefinition =  MyCacheManager.getValue(EhCacheConstant.API_DEFINITION, from.getD_id(),ApiDefinition.class);
            if (authFilter.validAuth(header, apiDefinition.getAlgo_sign(),header.getB_req())) {
                Map<String,Object> map;
                if (header.getData() !=null&& header.getData().contains("{")) {
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
                if ("GET".equals(type)) {
                    result = HttpRequest.get(url)
                            .form(map)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("POST".equals(type)) {
                    result = HttpRequest.post(url)
                            .form(map)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("PUT".equals(type)) {
                    result = HttpRequest.put(url)
                            .form(map)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("PATCH".equals(type)) {
                    result = HttpRequest.patch(url)
                            .form(map)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if ("DELETE".equals(type)) {
                    result = HttpRequest.delete(url)
                            .form(map)
                            .timeout(recClient.getTimeout())
                            .execute().body();
                }
                if (!header.getSync()) {
                    MyCacheManager.put(EhCacheConstant.ASYNC_HEADER, header.getCid(), header);
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
                return Result.newBuilder().setCode(2).setMsg(msg).setSignature(JSONUtil.toJsonStr(signature)).build();
            }
        }
    }
}
