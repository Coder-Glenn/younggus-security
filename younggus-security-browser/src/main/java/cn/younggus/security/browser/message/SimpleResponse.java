package cn.younggus.security.browser.message;

/**
 * @author Glenn.Zheng
 * @date 2018/6/12 21:49
 */
public class SimpleResponse {

    private Object message;

    public SimpleResponse(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
