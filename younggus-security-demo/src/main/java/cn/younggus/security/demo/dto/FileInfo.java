package cn.younggus.security.demo.dto;

/**
 * @author Glenn.Zheng
 * @date 2018/5/10 22:16
 */
public class FileInfo {


    public FileInfo() {}

    public FileInfo(String path) {
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
