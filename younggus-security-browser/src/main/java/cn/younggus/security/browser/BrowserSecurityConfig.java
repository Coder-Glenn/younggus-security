package cn.younggus.security.browser;

import cn.younggus.security.core.config.SecurityProperties;
import cn.younggus.security.core.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

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

    @Autowired
    private UserDetailsService userDetailsService;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SpringSocialConfigurer youngguysSocialSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(customerAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin() //表单登录
                .loginPage("/authencation/require")  //登录页面
                .loginProcessingUrl("/authentication/form")  //登录请求
                .successHandler(customerAuthenticationSuccessHandler)
                .failureHandler(customerAuthenticationFailureHandler)
            .and()
                .apply(youngguysSocialSecurityConfig) //添加spring social的过滤器
            .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
        //http.httpBasic()  //默认登录验证方式：弹出密码验证popover
            .and()
            .authorizeRequests()
            .antMatchers("/authencation/require",
                    securityProperties.getBrowser().getLoginPage(),
                    "/code/image")
            .permitAll() //授权
            .anyRequest()  //所有请求都需要身份验证
            .authenticated()
            .and()
            .csrf().disable(); //跨站攻击防护
    }
}
