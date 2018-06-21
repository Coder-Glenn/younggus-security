package cn.younggus.security.core.social.qq.api.service.impl;

import cn.younggus.security.core.social.qq.api.domain.QQUser;
import cn.younggus.security.core.social.qq.api.service.QQUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author Glenn.Zheng
 * @date 2018/6/21 23:38
 */
public class QQUserServiceImpl extends AbstractOAuth2ApiBinding implements QQUserService {

    private static String URL_GET_OPEN_ID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static String URL_GET_QQ_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQUserServiceImpl(String accessToken, String appId) {
        
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);  //将token放入到请求参数，默认是放到请求头
        this.appId = appId;

        String getOpenIdUrl = String.format(URL_GET_OPEN_ID, accessToken);
        String result = getRestTemplate().getForObject(getOpenIdUrl, String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    @Override
    public QQUser getUserInfo() throws Exception {

        String getQQUserInfoUrl = String.format(URL_GET_QQ_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(getQQUserInfoUrl, String.class);
        System.out.println(result);

        return objectMapper.readValue(result, QQUser.class);
    }
}
