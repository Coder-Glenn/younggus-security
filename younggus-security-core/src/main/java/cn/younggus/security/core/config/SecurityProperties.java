package cn.younggus.security.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 该类用于读取application.properties中所有以security开头的配置
 * @author Glenn.Zheng
 * @date 2018/6/12 22:01
 */
@ConfigurationProperties(prefix = "youngguys.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }
}
