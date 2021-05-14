package model;

public class ApiDefinition {

    private String id;
    private int type;
    private String version;
    private String algo_hash;
    private String algo_sign;
    private String para;
    private String serv;
    private String callBack;

    public ApiDefinition() {
    }

    public ApiDefinition(String id, int type, String version, String algo_hash, String algo_sign, String para, String serv, String callBack) {
        this.id = id;
        this.type = type;
        this.version = version;
        this.algo_hash = algo_hash;
        this.algo_sign = algo_sign;
        this.para = para;
        this.serv = serv;
        this.callBack = callBack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAlgo_hash() {
        return algo_hash;
    }

    public void setAlgo_hash(String algo_hash) {
        this.algo_hash = algo_hash;
    }

    public String getAlgo_sign() {
        return algo_sign;
    }

    public void setAlgo_sign(String algo_sign) {
        this.algo_sign = algo_sign;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }
}
