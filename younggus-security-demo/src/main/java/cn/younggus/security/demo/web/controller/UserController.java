package cn.younggus.security.demo.web.controller;

import cn.younggus.security.demo.dto.User;
import cn.younggus.security.demo.dto.UserQueryCondition;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Glenn.Zheng
 * @date 2018/4/29 10:52
 */
@RestController //标明此controller用于提供Rest API
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/currentUser")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/me")
    public Object getCurrentUserInfo(Authentication authentication) {
        //直接返回的效果同上：Spring Security会自动从securityContext中获取authentication对象。
        return authentication;
    }

    @GetMapping("/userDetail")
    public Object getCurrentUserDetail(@AuthenticationPrincipal UserDetails user) {
        //只返回Authentication中的Principal
        return user;
    }


    /**
     * we will use @Valid and BindingResult together,
     * valid result will collected by BindingResult
     * @param user
     * @param //error
     * @return
     */
    @PostMapping
    public User crete(@Valid @RequestBody User user, BindingResult errors) {
            logger.info("create");
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage() ));
        }

        logger.info(user.getId());
        logger.info(user.getUsername());
        logger.info(user.getPassword());
        logger.info(user.getBirthday() + "");
        user.setId("1");
        return user;
    }

    @PutMapping(value = "/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error ->{
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + " " +error.getDefaultMessage();
//                System.out.println(message);
                System.out.println(error.getDefaultMessage());
            });
        }

        logger.info(user.getId());
        logger.info(user.getUsername());
        logger.info(user.getPassword());
        logger.info(user.getBirthday() + "");
        user.setId("1");
        return user;
    }

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public List<User> query(@RequestParam(name = "nickname", defaultValue = "Glenn", required = false) String username) {
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    //直接传递一个对象
//    public List<User> query(UserQueryCondition condition) {
//        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping
    //传递一个封装了分页信息的对象Pageable,包括: page(第几页), size(查询几条),offset. sort(怎么排序)
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 3, size = 20, sort = "username,asc") Pageable pageable) {
        logger.info("query");
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User("1"));
        users.add(new User("2"));
        users.add(new User("3"));
        return users;
    }

    //@RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUserDetailInfo(@PathVariable(value = "id", required = true) String userId) {
        logger.info("getUserDetailInfo");
//        throw new UserNotExistException(userId);
//        throw new RuntimeException("test runtimeException");
        User user = new User("1");
        user.setUsername("glenn");
        user.setPassword("123");
        user.setBirthday(new Date());
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
