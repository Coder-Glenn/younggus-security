package cn.younggus.config;

import org.springframework.stereotype.Component;

/**
 * @author Glenn.Zheng
 * @date 2018/6/12 22:01
 */
public class BrowserProperties {

    private String loginPage = "/customer-login.html";  //默认登录页

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
