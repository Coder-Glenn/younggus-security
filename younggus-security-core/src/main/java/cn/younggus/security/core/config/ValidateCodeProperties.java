package cn.younggus.security.core.config;

/**
 * @author Glenn.Zheng
 * @date 2018/6/20 23:28
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
