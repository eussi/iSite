package com.eussi.blog.base.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author - wangxm
 */
public class FileOperUtils {
    // 文件允许格式
    private static List<String> allowFiles = Arrays.asList(".gif", ".png", ".jpg", ".jpeg", ".bmp");
    private final static String PREFIX_VIDEO="video/";
    private final static String PREFIX_IMAGE="image/";

    /**
     * 文件类型是否支持
     *
     * @param fileName
     * @return
     */
    public static boolean checkFileType(String fileName) {
        Iterator<String> type = allowFiles.iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取路径中文件名
     * @param path
     * @return
     */
    public static String getFilename(String path) {
        int pos = path.lastIndexOf(File.separator);
        return path.substring(pos + 1);
    }

    /**
     * 获取后缀
     * @param filename
     * @return
     */
    public static String getSuffix(String filename) {
        int pos = filename.lastIndexOf(".");
        return filename.substring(pos);
    }

    /**
     * 写入文件
     * @param bytes
     * @param dest
     * @throws IOException
     */
    public static void writeByteArrayToFile(byte[] bytes, String dest) throws IOException {
        FileUtils.writeByteArrayToFile(new File(dest), bytes);
    }


}
