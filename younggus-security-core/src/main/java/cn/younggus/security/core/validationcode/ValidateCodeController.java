package cn.younggus.security.core.validationcode;

import cn.younggus.security.core.common.CommonContants;
import cn.younggus.security.core.config.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Glenn.Zheng
 * @date 2018/6/20 22:07
 */
@RestController
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ImageCodeGenerator imageCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 生成image code
        ImageCode imageCode = imageCodeGenerator.createImageCode(new ServletWebRequest(request));
        // 2. 存入到session
        sessionStrategy.setAttribute(new ServletWebRequest(request), CommonContants.IMAGE_CODE, imageCode);
        // 3. 写到response
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
