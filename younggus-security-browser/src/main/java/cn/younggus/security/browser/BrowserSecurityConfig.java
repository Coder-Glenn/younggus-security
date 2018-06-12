package cn.younggus.security.browser;

import cn.younggus.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    public SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //表单登录
            .loginPage("/authencation/require")  //登录页面
            .loginProcessingUrl("/authentication/form")  //登录请求
        //http.httpBasic()  //默认登录验证方式：弹出密码验证popover
            .and()
            .authorizeRequests()
            .antMatchers("/authencation/require", securityProperties.getBrowserProperties().getLoginPage()).permitAll() //授权
            .anyRequest()  //所有请求都需要身份验证
            .authenticated()
            .and()
            .csrf().disable(); //跨站攻击防护
    }
}
