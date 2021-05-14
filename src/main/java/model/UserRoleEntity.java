package model;

/**
 * @author zyf
 */
public class UserRoleEntity {

    private String credit_code;
    private String role_type;
    private String cert_ssl_name;
    private String cert_ssl_pem;
    private String cert_sign_name;
    private String cert_sign_pem;

    public UserRoleEntity() {

    }

    public UserRoleEntity(String credit_code, String role_type) {
        this.credit_code = credit_code;
        this.role_type = role_type;
    }

    public String getCredit_code() {
        return credit_code;
    }

    public void setCredit_code(String credit_code) {
        this.credit_code = credit_code;
    }

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }

    public String getCert_ssl_name() {
        return cert_ssl_name;
    }

    public void setCert_ssl_name(String cert_ssl_name) {
        this.cert_ssl_name = cert_ssl_name;
    }

    public String getCert_ssl_pem() {
        return cert_ssl_pem;
    }

    public void setCert_ssl_pem(String cert_ssl_pem) {
        this.cert_ssl_pem = cert_ssl_pem;
    }

    public String getCert_sign_name() {
        return cert_sign_name;
    }

    public void setCert_sign_name(String cert_sign_name) {
        this.cert_sign_name = cert_sign_name;
    }

    public String getCert_sign_pem() {
        return cert_sign_pem;
    }

    public void setCert_sign_pem(String cert_sign_pem) {
        this.cert_sign_pem = cert_sign_pem;
    }
}
