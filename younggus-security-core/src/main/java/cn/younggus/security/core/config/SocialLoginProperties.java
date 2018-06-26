package cn.younggus.security.core.config;

/**
 * @author Glenn.Zheng
 * @date 2018/6/26 22:14
 */
public class SocialLoginProperties {

    private QQLoginProperties qq = new QQLoginProperties();

    public QQLoginProperties getQq() {
        return qq;
    }

    public void setQq(QQLoginProperties qq) {
        this.qq = qq;
    }
}
