package cn.younggus.security.core.config;

import static cn.younggus.security.core.config.LoginType.JSON;

/**
 * @author Glenn.Zheng
 * @date 2018/6/12 22:01
 */
public class BrowserProperties {

    private String loginPage = "/customer-login.html";  //默认登录页

    private LoginType loginType = JSON;

    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
