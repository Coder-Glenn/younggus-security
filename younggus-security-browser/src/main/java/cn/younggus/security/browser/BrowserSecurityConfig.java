package cn.younggus.security.browser;

import cn.younggus.security.core.config.SecurityProperties;
import cn.younggus.security.core.filter.ValidateCodeFilter;
import cn.younggus.security.core.validationcode.ValidateCodeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置入口
 * @author Glenn.Zheng
 * @date 2018/5/29 22:55
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler customerAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customerAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(customerAuthenticationFailureHandler);

        http
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin() //表单登录
            .loginPage("/authencation/require")  //登录页面
            .loginProcessingUrl("/authentication/form")  //登录请求
            .successHandler(customerAuthenticationSuccessHandler)
            .failureHandler(customerAuthenticationFailureHandler)
        //http.httpBasic()  //默认登录验证方式：弹出密码验证popover
            .and()
            .authorizeRequests()
            .antMatchers("/authencation/require",
                    securityProperties.getBrowserProperties().getLoginPage(),
                    "/code/image")
            .permitAll() //授权
            .anyRequest()  //所有请求都需要身份验证
            .authenticated()
            .and()
            .csrf().disable(); //跨站攻击防护
    }
}
