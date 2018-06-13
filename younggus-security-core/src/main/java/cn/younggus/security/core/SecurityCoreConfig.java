package cn.younggus.security.core;

import cn.younggus.security.core.config.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 该类用于开启配置读取到指定类的功能
 * @author Glenn.Zheng
 * @date 2018/6/12 22:04
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
