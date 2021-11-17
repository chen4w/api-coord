package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.exception.ServiceException;
import repchain.inter.cooperation.middleware.model.Header;
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
    public void saveData(String cid, Header header, Object result, String sendFile, String downloadFile) throws SQLException {
        if (StrUtil.isBlank(cid)) {
            throw new ServiceException("cid 不能为空");
        }
        if (header == null) {
            throw new ServiceException("header 不能为空");
        }
        Entity entity = Entity.create(EhCacheConstant.PERSISTENCE).set("cid", cid).set("header", header).set("time", new Date());
        if (result != null) {
            entity = entity.set("result", result);
        }
        if (sendFile != null) {
            String dir = YamlUtils.jarPath + "/file/send/"+cid+"/"+System.currentTimeMillis();
            File file = new File(sendFile);
            if (!file.exists()) {
                throw new ServiceException("需要保存的文件不存在");
            }
            File fileDir = new File(dir);
            if (!fileDir.exists()) {
                FileUtil.mkdir(fileDir);
            }
            FileUtil.copy(file, fileDir, false);
            entity = entity.set("send_file", file.getAbsolutePath());
        }
        if (downloadFile != null) {
            File file = new File(downloadFile);
            String dir = YamlUtils.jarPath + "/file/download/"+cid+"/"+System.currentTimeMillis();
            File fileDir = new File(dir);
            if (!fileDir.exists()) {
                FileUtil.mkdir(fileDir);
            }
            file = FileUtil.copy(file, fileDir, false);
            entity = entity.set("header", file.getAbsolutePath());
        }
        db.insertForGeneratedKey(
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

    public static void main(String[] args) {
        File file = new File("/Volumes/DATA/test");
        File fil2e = new File("/Volumes/DATA/upload");
        System.out.println(FileUtil.copy(file,fil2e,false));
    }
}
