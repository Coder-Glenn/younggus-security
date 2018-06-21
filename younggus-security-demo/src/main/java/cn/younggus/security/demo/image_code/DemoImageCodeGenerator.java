package cn.younggus.security.demo.image_code;

import cn.younggus.security.core.config.SecurityProperties;
import cn.younggus.security.core.validationcode.ImageCode;
import cn.younggus.security.core.validationcode.ImageCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Glenn.Zheng
 * @date 2018/6/21 22:05
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ImageCodeGenerator {

    @Override
    public ImageCode createImageCode(ServletWebRequest request) {
        System.out.println("第三方实现image code的生成");
        return null;
    }

    @Override
    public void setSecurityProperties(SecurityProperties securityProperties) {

    }
}
