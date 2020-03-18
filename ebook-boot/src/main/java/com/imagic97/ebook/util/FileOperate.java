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
    public static boolean fileUpload(MultipartFile multipartFile, String path, String filename) {
        File saveFile = new File(path + filename);
        if (saveFile.exists()) {
            throw new MessageException("文件已存在");
        }
        try {
            multipartFile.transferTo(saveFile);
        } catch (IllegalStateException | IOException e) {
            throw new MessageException(CommonMessageCode.INTERNAL_SERVER_ERROR);
        }
        return true;
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return false;
        }
    }
}
