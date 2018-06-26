package cn.younggus.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author Glenn.Zheng
 * @date 2018/6/26 21:46
 */
@Configuration
@EnableSocial
public class QQSocialConfig extends SocialConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // connectionFactoryLocator 是获取 connectionFactory, 不同的服务商有不同的实现
        // 第三个参数是加解密算法，此处不加解密
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // 设置表名前缀
        repository.setTablePrefix("test_");
        return repository;
        //这是操作数据库的默认实现
    }

    //配置需要加到spring security 过滤器链中的过滤器
    @Bean
    public SpringSocialConfigurer youngguysSocialSecurityConfig() {
        return new SpringSocialConfigurer();
    }
}
