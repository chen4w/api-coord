package repchain.inter.cooperation.middleware.model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhc
 * @version 1.0
 * @className Datasource
 * @date 2021年11月09日 9:40 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MyDatasource {
    private String url;
    private String pass;
    private String user;
    private String driver;
    private String showSql;
    private String formatSql;
    private String showParams;
    private String sqlLevel;
}
