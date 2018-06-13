package cn.younggus.security.demo.exception;

/**
 * @author Glenn.Zheng
 * @date 2018/5/9 22:51
 */
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1105933806310748953L;

    private String id;

    public UserNotExistException(String id) {
        super("user does not exist!");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
