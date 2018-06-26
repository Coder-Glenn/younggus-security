package cn.younggus.security.core.social.qq.connect;

import cn.younggus.security.core.social.qq.api.service.QQUserService;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author Glenn.Zheng
 * @date 2018/6/26 21:42
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQUserService> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQApiAdapter());
    }
}
