package cn.younggus.web.controller;

import cn.younggus.dto.User;
import cn.younggus.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Glenn.Zheng
 * @date 2018/4/29 10:52
 */
@RestController //标明此controller用于提供Rest API
public class UserController {

//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public List<User> query(@RequestParam(name = "nickname", defaultValue = "Glenn", required = false) String username) {
//        System.out.println(username);
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

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    //传递一个封装了分页信息的对象Pageable,包括: page(第几页), size(查询几条),offset. sort(怎么排序)
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 3, size = 20, sort = "username,asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
