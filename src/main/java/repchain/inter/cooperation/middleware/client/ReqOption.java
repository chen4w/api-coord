package repchain.inter.cooperation.middleware.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import repchain.inter.cooperation.middleware.utils.SnowIdGenerator;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className ReqOption
 * @date 2021年11月03日 10:40 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqOption implements Serializable {
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public static final String GET = "GET";
    public static final String  POST= "POST";
    public static final String DELETE= "DELETE";
    public static final String PUT= "PUT";
    public static final String PATCH= "PATCH";
    private static final long serialVersionUID = 9074542847976577188L;
    private int seq = 1;
    private int isEnd = TRUE;
    private int bReq = TRUE;
    private String callbackMethod = GET;
    private String callbackUrl = "/";
    private String cid = SnowIdGenerator.getId();
    private String data;
    private String serviceId;
    private String url;
    private String method;
    private int sync = TRUE;
    private String filepath;
    private String fileHash;
    private int reqSave = FALSE;
    private int resultSave = FALSE;
    private int fileSave = FALSE;
    private String headers;
}
