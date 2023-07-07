package top.jxqggg.demo.service.smb;


/**
 * TODO
 *
 * @author JiangQiang 2023/7/5 12:58
 */

public class SambaConfig {

    private String host;
    private String domain;
    private String userName;
    private String password;

    public SambaConfig(String host, String userName, String password) {
        this.host = host;
        this.userName = userName;
        this.password = password;
    }

    public SambaConfig(String host, String userName, String password, String domain) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.domain = domain;
    }

    public String getHost() {
        return host;
    }

    public String getDomain() {
        return domain;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
