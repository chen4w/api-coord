package model;

public class ApiServAndAck {

    private String id;
    private String d_id;
    private String version;
    private String e_id;
    private String addr;
    private int port;

    public ApiServAndAck() {
    }

    public ApiServAndAck(String id, String d_id, String version, String e_id, String addr, int port) {
        this.id = id;
        this.d_id = d_id;
        this.version = version;
        this.e_id = e_id;
        this.addr = addr;
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
