package repchain.inter.cooperation.middleware.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhc
 * @version 1.0
 * @className PerVo
 * @date 2021年11月18日 11:40 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PerVo {
   private String cid;
   private Header header;
   private Object result;
   private String sendFile;
   private String downloadFile;
}
