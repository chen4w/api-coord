package repchain.inter.cooperation.middleware.quartz;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.Page;
import cn.hutool.db.PageResult;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.model.SysCert;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.utils.PkUtil;
import repchain.inter.cooperation.middleware.utils.RequestAck;
import repchain.inter.cooperation.middleware.utils.SqliteUtil;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

/**
 * @author lhc
 * @version 1.0
 * @className CommitJob
 * @date 2021年11月26日 4:15 下午
 * @description 描述
 */
public class CommitJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(CommitJob.class);

    private final Db db = SqliteUtil.getInstance();


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            int i = 1;
            while (true) {
                PageResult<Entity> result = db.page(Entity.create(EhCacheConstant.REQ_ACK_PROOF), new Page(i, 10));
                for (Entity entity : result) {
                    // 创建请求实例，若用Spring 此处可以创建javabean
                    RepChain repchain = YamlUtils.getRepchain();
                    RequestAck requestAck = new RequestAck(repchain.getHost());
                    SysCert sysCert = PkUtil.getSysCert(repchain);
                    String key = (String) entity.get("id");
                    String[] keys = key.split("_");
                    Long now = System.currentTimeMillis();
                    Long saveTime = Long.parseLong(keys[2]) + repchain.getBlockTime() * 60 * 1000;
                    if (saveTime > now) {
                        String value = (String) entity.get("obj");
                        JSONObject jsonObject = requestAck.rb(JSONUtil.toBean(value, ReqAckProof.class), sysCert);
                        // 若果有错误信息，则提交存证数据失败
                        if (!StrUtil.isBlankIfStr(jsonObject.get("err"))) {
                            logger.error("提交区块链数据失败：" + jsonObject);
                        }
                    }
                }
                if (result.isLast()) {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
