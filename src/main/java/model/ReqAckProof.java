package model;

/**
 * @author zyf
 */
public class ReqAckProof {

    private String cid;
    private String e_from;
    private String e_to;
    private String method;
    private boolean b_req;
    private boolean b_end;
    private int seq;
    private String hash;
    private String hash_claim;
    private Long tm_create;
    private Signature sign_r;
    private Signature sign_c;

    public ReqAckProof() {
    }

    public ReqAckProof(String cid, String e_from, String e_to, String method, boolean b_req, boolean b_end, int seq, String hash, String hash_claim, Long tm_create, Signature sign_r, Signature sign_c) {
        this.cid = cid;
        this.e_from = e_from;
        this.e_to = e_to;
        this.method = method;
        this.b_req = b_req;
        this.b_end = b_end;
        this.seq = seq;
        this.hash = hash;
        this.hash_claim = hash_claim;
        this.tm_create = tm_create;
        this.sign_r = sign_r;
        this.sign_c = sign_c;
    }

    public static class Signature {

        private String eid;
        private String cert_name;
        private String hash;
        private Long timeCreate;
        private String sign;

        public Signature() {
        }

        public Signature(String eid, String cert_name, String hash, Long timeCreate, String sign) {
            this.eid = eid;
            this.cert_name = cert_name;
            this.hash = hash;
            this.timeCreate = timeCreate;
            this.sign = sign;
        }

        public String getEid() {
            return eid;
        }

        public void setEid(String eid) {
            this.eid = eid;
        }

        public String getCert_name() {
            return cert_name;
        }

        public void setCert_name(String cert_name) {
            this.cert_name = cert_name;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public Long getTimeCreate() {
            return timeCreate;
        }

        public void setTimeCreate(Long timeCreate) {
            this.timeCreate = timeCreate;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getE_from() {
        return e_from;
    }

    public void setE_from(String e_from) {
        this.e_from = e_from;
    }

    public String getE_to() {
        return e_to;
    }

    public void setE_to(String e_to) {
        this.e_to = e_to;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isB_req() {
        return b_req;
    }

    public void setB_req(boolean b_req) {
        this.b_req = b_req;
    }

    public boolean isB_end() {
        return b_end;
    }

    public void setB_end(boolean b_end) {
        this.b_end = b_end;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash_claim() {
        return hash_claim;
    }

    public void setHash_claim(String hash_claim) {
        this.hash_claim = hash_claim;
    }

    public Long getTm_create() {
        return tm_create;
    }

    public void setTm_create(Long tm_create) {
        this.tm_create = tm_create;
    }

    public Signature getSign_r() {
        return sign_r;
    }

    public void setSign_r(Signature sign_r) {
        this.sign_r = sign_r;
    }

    public Signature getSign_c() {
        return sign_c;
    }

    public void setSign_c(Signature sign_c) {
        this.sign_c = sign_c;
    }
}
