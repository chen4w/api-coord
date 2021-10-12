package model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className Repchain
 * @date 2021年10月12日 4:09 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Repchain implements Serializable {

    private static final long serialVersionUID = 2532596334359427852L;
    private String host;
    private List<InterCo> interCo;
}
