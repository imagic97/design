package com.imagic97.ebook.util;

import com.imagic97.ebook.common.CommonMessageCode;
import com.imagic97.ebook.exception.MessageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author imagic
 */
public class FileOperate {
    /**
     * 上传文件转存
     */
    public boolean fileUpload(MultipartFile multipartFile, long user_id) {
        String originalFilename = String.valueOf(user_id) + "-" + multipartFile.getOriginalFilename();
        String path = getClass().getResource("/").getPath() + "static/book/";
        File saveFile = new File(path + originalFilename);
        if (saveFile.exists()) {
           return false;
        }
        try {
            multipartFile.transferTo(saveFile);
        } catch (IllegalStateException | IOException e) {
            throw new MessageException(CommonMessageCode.INTERNAL_SERVER_ERROR);
        }
        return true;
    }
}
