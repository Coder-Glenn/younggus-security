package cn.younggus.security.core.social.qq.config;

import cn.younggus.security.core.config.SecurityProperties;
import cn.younggus.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author Glenn.Zheng
 * @date 2018/6/26 22:18
 * 构建QQConnectionFactory
 */
@Configuration
@ConditionalOnProperty(prefix = "youngguys.security.social.qq", name = "appId")  //当这个值不为空时配置才生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        String appId = securityProperties.getSocial().getQq().getAppId();
        String appSecret = securityProperties.getSocial().getQq().getAppSecret();
        String providerId = securityProperties.getSocial().getQq().getProviderId();
        return new QQConnectionFactory(providerId, appId, appSecret);
    }
}
