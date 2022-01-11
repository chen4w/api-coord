package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.model.SysCert;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.service.TransactionCommit;
import repchain.inter.cooperation.middleware.utils.MyCacheManager;
import repchain.inter.cooperation.middleware.utils.PkUtil;
import repchain.inter.cooperation.middleware.utils.RequestAck;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

/**
 * @author lhc
 * @version 1.0
 * @className TransactionCommit
 * @date 2021年10月27日 9:54 上午
 * @description 描述
 */
public class TransactionCommitImpl implements TransactionCommit {

    private static final Logger logger = LoggerFactory.getLogger(TransactionCommitImpl.class);

    @Override
    public void saveProof(ReqAckProof rb) {
        MyCacheManager.put(EhCacheConstant.REQ_ACK_PROOF, rb.getCid() +"_"+ rb.getHash() +"_"+ rb.getTm_create(), rb);
        // 创建请求实例，若用Spring 此处可以创建javabean
        RepChain repchain = YamlUtils.getRepchain();
        RequestAck requestAck = new RequestAck(repchain.getHost());
        SysCert sysCert = PkUtil.getSysCert(repchain);
        JSONObject jsonObject = requestAck.rb(rb, sysCert);
        if (jsonObject != null) {
            // 若果有错误信息，则提交存证数据失败
            if (!StrUtil.isBlankIfStr(jsonObject.get("err"))) {
                logger.error("提交区块链数据失败：" + jsonObject);
            }
        } else {
            logger.error("异步存证返回结果为空");
        }

    }
}
