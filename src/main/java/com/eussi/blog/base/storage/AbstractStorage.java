package com.eussi.blog.base.storage;

import com.eussi.blog.base.exception.BlogException;
import com.eussi.blog.base.utils.FileOperUtils;
import com.eussi.blog.base.utils.FilePathUtils;
import com.eussi.blog.base.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangxm
 */
@Slf4j
public abstract class AbstractStorage implements Storage {

    protected void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BlogException("文件不能为空");
        }

        if (!FileOperUtils.checkFileType(file.getOriginalFilename())) {
            throw new BlogException("文件格式不支持");
        }
    }

    @Override
    public String store(MultipartFile file, String basePath) throws Exception {
        validateFile(file);
        return writeToStore(file.getBytes(), basePath, file.getOriginalFilename());
    }

    @Override
    public String storeScale(MultipartFile file, String basePath, int maxWidth) throws Exception {
        validateFile(file);
        byte[] bytes = ImageUtils.scaleByWidth(file, maxWidth);
        return writeToStore(bytes, basePath, file.getOriginalFilename());
    }

    @Override
    public String storeScale(MultipartFile file, String basePath, int width, int height) throws Exception {
        validateFile(file);
        byte[] bytes = ImageUtils.screenshot(file, width, height);
        return writeToStore(bytes, basePath, file.getOriginalFilename());
    }

    public String writeToStore(byte[] bytes, String src, String originalFilename) throws Exception {
        String path = FilePathUtils.wholePathName(src, originalFilename);
        return writeToStore(bytes, path);
    }
}
