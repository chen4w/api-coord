package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONUtil;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.exception.ServiceException;
import repchain.inter.cooperation.middleware.model.PerVo;
import repchain.inter.cooperation.middleware.model.yml.FileYml;
import repchain.inter.cooperation.middleware.service.Persistence;
import repchain.inter.cooperation.middleware.utils.SqliteUtil;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className PersistenceImpl
 * @date 2021年11月08日 5:04 下午
 * @description 描述
 */
public class PersistenceImpl implements Persistence {

    private final Db db = SqliteUtil.getInstance();

    @Override
    public Object saveData(PerVo perVo) throws SQLException {
        if (StrUtil.isBlank(perVo.getCid())) {
            throw new ServiceException("cid 不能为空");
        }
        if (perVo.getHeader() == null) {
            throw new ServiceException("header 不能为空");
        }
        Entity entity = Entity.create(EhCacheConstant.PERSISTENCE).set("cid", perVo.getCid()).set("header", JSONUtil.toJsonStr(perVo.getHeader())).set("time", new Date());
        if (perVo.getResult() != null) {
            entity = entity.set("result", JSONUtil.toJsonStr(perVo.getResult()));
        }
        String parentPath = YamlUtils.jarPath + "/file/backup";
        if (YamlUtils.middleConfig.getMiddleware().getFile() != null) {
            FileYml fileYml = YamlUtils.middleConfig.getMiddleware().getFile();
            if (fileYml.getBackupPath()!=null) {
                parentPath = fileYml.getTemp()+"/backup";
            }
        }
        if (perVo.getSendFile() != null) {
            File file = new File(perVo.getSendFile());
            if (!file.exists()) {
                throw new ServiceException("需要保存的文件不存在");
            }
            File fileDir = new File(parentPath);
            if (!fileDir.exists()) {
                FileUtil.mkdir(fileDir);
            }
            FileUtil.copy(file, fileDir, false);
            entity = entity.set("send_file", file.getAbsolutePath());
        }
        if (perVo.getDownloadFile() != null) {
            File file = new File(perVo.getDownloadFile());
            File fileDir = new File(parentPath);
            if (!fileDir.exists()) {
                FileUtil.mkdir(fileDir);
            }
            file = FileUtil.copy(file, fileDir, false);
            entity = entity.set("download_file", file.getAbsolutePath());
        }
        return db.insertForGeneratedKey(
                entity
        );
    }

    @Override
    public List<Entity> get(String cid) throws SQLException {
        List<Entity> entity = db.query("SELECT obj FROM " + EhCacheConstant.PERSISTENCE + " where cid = ?", cid);
        if (entity == null || entity.isEmpty()) {
            return null;
        }
        return entity;
    }
}
