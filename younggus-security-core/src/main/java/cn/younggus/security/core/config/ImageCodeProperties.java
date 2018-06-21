package cn.younggus.security.core.config;

/**
 * @author Glenn.Zheng
 * @date 2018/6/20 23:26
 */
public class ImageCodeProperties {

    //验证码宽度
    private int width = 45;

    //验证码高度
    private int height = 25;

    //验证码长度
    private int length = 4;

    //验证码过期时间
    private int expiredIn = 60;

    //需要验证码的URL
    private String interceptUrl = "/authentication/form";

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(int expiredIn) {
        this.expiredIn = expiredIn;
    }

    public String getInterceptUrl() {
        return interceptUrl;
    }

    public void setInterceptUrl(String interceptUrl) {
        this.interceptUrl = interceptUrl;
    }
}
