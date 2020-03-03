package com.imagic97.ebook.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * @author imagic
 */
public class FileOperate {
    public  String fileUpload(MultipartFile file) {
        // 获取上传文件路径
        String uploadPath = file.getOriginalFilename();
        // 获取上传文件的后缀
        String fileSuffix = uploadPath.substring(uploadPath.lastIndexOf(".") + 1, uploadPath.length());
        if (!fileSuffix.equals("epub")) {
            return null;
        }
        String path = getClass().getResource("/").getPath() + "static/book/";
        // 上传文件名
        String fileName = new Date().getTime() + new Random().nextInt(100) + "." + fileSuffix;
        File savefile = new File(path + fileName);
        if (!savefile.getParentFile().exists()) {
            savefile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(savefile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
