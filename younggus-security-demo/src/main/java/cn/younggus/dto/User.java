package cn.younggus.dto;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Glenn.Zheng
 * @date 2018/4/29 10:56
 *
 * How to user JsonView：
 *   1. use interface to define view.
 *   2. define view on get method.
 *   3. choose view on controller.
 */
public class User {

    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    private String username;

    private String password;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
