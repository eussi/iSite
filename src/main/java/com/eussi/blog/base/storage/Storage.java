package com.eussi.blog.base.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangxm
 *
 */
public interface Storage {

	/**
	 * 存储图片
	 * @param file
	 * @param basePath
	 * @return
	 * @throws java.io.IOException
	 */
	String store(MultipartFile file, String basePath) throws Exception;

	/**
	 * 存储压缩图片
	 * @param file
	 * @param basePath
	 * @return
	 * @throws java.io.IOException
	 */
	String storeScale(MultipartFile file, String basePath, int maxWidth) throws Exception;

	/**
	 * 存储压缩图片
	 * @param file
	 * @param basePath
	 * @return
	 * @throws java.io.IOException
	 */
	String storeScale(MultipartFile file, String basePath, int width, int height) throws Exception;

	/**
	 * 删除路径
	 * @param storePath
	 */
	void deleteFile(String storePath);

    /**
     * 写入文件存储
     * @param bytes
     * @param pathAndFileName
     * @return
     * @throws Exception
     */
	String writeToStore(byte[] bytes, String pathAndFileName) throws Exception;
}
