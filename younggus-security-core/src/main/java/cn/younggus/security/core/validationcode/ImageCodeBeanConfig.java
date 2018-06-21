package cn.younggus.security.core.validationcode;

import cn.younggus.security.core.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Glenn.Zheng
 * @date 2018/6/21 21:53
 */
@Configuration
public class ImageCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 系统启动时寻找名字为imageCodeGenerator的Bean，这个Bean可以在其他模块实现
     * 没有则执行该方法
     * 该配置可以做到以增量的方式去适应变化
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ImageCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGeneratorImpl();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

}
