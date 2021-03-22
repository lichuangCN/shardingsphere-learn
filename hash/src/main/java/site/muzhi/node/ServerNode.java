package site.muzhi.node;

/**
 * @author lichuang
 * @date 2021/03/22
 * @description
 */
public class ServerNode<T> {

    private String ip;
    private String name;

    public ServerNode(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 以服务的ip作为key
     * @return
     */
    @Override
    public String toString() {
        return ip;
    }
}
