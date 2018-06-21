package cn.younggus.security.core.validationcode;

import cn.younggus.security.core.config.SecurityProperties;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Glenn.Zheng
 * @date 2018/6/21 21:46
 */
public interface ImageCodeGenerator {

    //验证码生成
    ImageCode createImageCode(ServletWebRequest request);

    void setSecurityProperties(SecurityProperties securityProperties);
}
