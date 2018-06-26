package cn.younggus.security.core.social.qq.connect;

import cn.younggus.security.core.social.qq.api.service.QQUserService;
import cn.younggus.security.core.social.qq.api.service.impl.QQUserServiceImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author Glenn.Zheng
 * @date 2018/6/26 21:17
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQUserService> {

    private String appId;  //服务商提供的ID

    private String appSecret;  //服务商提供的密码

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";  //将用户导向认证服务器的地址,获取授权码

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token"; //拿到授权码以后申请令牌的地址

    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQUserService getApi(String accessToken) {
        return new QQUserServiceImpl(accessToken, appId);
    }


}
