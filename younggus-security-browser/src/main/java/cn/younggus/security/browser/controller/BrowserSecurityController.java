package cn.younggus.security.browser.controller;

import cn.younggus.security.browser.message.SimpleResponse;
import cn.younggus.config.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Glenn.Zheng
 * @date 2018/6/12 0:44
 */
@RestController
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    public SecurityProperties securityProperties;

    /**
     * 用于处理身份认证
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authencation/require")
    public SimpleResponse rqeuireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            System.out.println("引发跳转的请求是：" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowserProperties().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请登录后重试...");
    }
}
