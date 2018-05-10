package cn.younggus.web.controller;

import cn.younggus.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author Glenn.Zheng
 * @date 2018/5/10 22:14
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    String folder = "D:\\younggus-security\\younggus-security-demo\\src\\main\\resources\\files";

    @PostMapping
    public FileInfo upload(MultipartFile files) throws IOException {
        logger.info("upload");

        System.out.println("param name: " + files.getName());
        System.out.println("file name: " + files.getOriginalFilename());
        System.out.println("file size: " + files.getSize());

        File localFile = new File(folder, files.getOriginalFilename());

        //上传的文件保存到本地
        files.transferTo(localFile);

        FileInfo fileInfo = new FileInfo(localFile.getAbsolutePath());
        return fileInfo;
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("download");

        InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
        OutputStream outputStream = response.getOutputStream();

        try {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + id);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }

}
