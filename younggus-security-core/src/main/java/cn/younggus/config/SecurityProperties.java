package cn.younggus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 该类用于读取application.properties中所有以security开头的配置
 * @author Glenn.Zheng
 * @date 2018/6/12 22:01
 */
@ConfigurationProperties(prefix = "spring.security")
@Component
public class SecurityProperties {

    private BrowserProperties browserProperties = new BrowserProperties();

    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }

    public void setBrowserProperties(BrowserProperties browserProperties) {
        this.browserProperties = browserProperties;
    }
}
