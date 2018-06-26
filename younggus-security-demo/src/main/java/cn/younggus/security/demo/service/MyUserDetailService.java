package cn.younggus.security.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author Glenn.Zheng
 * @date 2018/6/11 20:29
 */
@Component
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username = " + username);
        // 根据用户名从数据库查找user
        // 下面这个user已经实现了UserDetails接口，是spring security提供的默认实现，new User(username, password, authoritys)，username password authority都是需要从数据库读取出来的
        //User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
        //在实现UserDetail的类中，实现用户信息校验的逻辑,即override一下方法:

//        public boolean isEnabled() {
//            return this.enabled;
//        }
//
//        public boolean isAccountNonExpired() {
//            return this.accountNonExpired;
//        }
//
//        public boolean isAccountNonLocked() {
//            return this.accountNonLocked;
//        }
//
//        public boolean isCredentialsNonExpired() {
//            return this.credentialsNonExpired;
//        }
        String password = passwordEncoder.encode("password");
        System.out.println("表单登录password=" + password);
        return new User(username, passwordEncoder.encode("password"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//      return new User(username, "password1234", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        //Spring Social会根据服务商提供的openId拿到user Id
        logger.info("社交登录用户id = " + userId);
        //根据ID查找用户信息
        //根据用户信息判断用户是否被lock, 冻结等信息
        String password = passwordEncoder.encode("password");
        logger.info("数据库密码是：" + password);
        return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
