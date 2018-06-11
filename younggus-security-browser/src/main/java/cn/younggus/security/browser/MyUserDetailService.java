package cn.younggus.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Glenn.Zheng
 * @date 2018/6/11 20:29
 */
@Component
public class MyUserDetailService implements UserDetailsService {

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
        String password = passwordEncoder.encode("password1234");
        System.out.println("password=" + password);
        return new User(username, passwordEncoder.encode("password1234"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//      return new User(username, "password1234", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
