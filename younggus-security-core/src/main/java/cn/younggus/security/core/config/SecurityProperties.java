package cn.younggus.security.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 该类用于读取application.properties中所有以security开头的配置
 * @author Glenn.Zheng
 * @date 2018/6/12 22:01
 */
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowserProperties() {
        return browser;
    }

    public void setBrowserProperties(BrowserProperties browser) {
        this.browser = browser;
    }
}
