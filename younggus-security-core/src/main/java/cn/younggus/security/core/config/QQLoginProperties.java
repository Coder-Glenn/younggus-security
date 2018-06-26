package cn.younggus.security.core.config;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author Glenn.Zheng
 * @date 2018/6/26 22:12
 */
public class QQLoginProperties extends SocialProperties {

    //父类有appId, appSecret

    private String providerId = "QQ";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
