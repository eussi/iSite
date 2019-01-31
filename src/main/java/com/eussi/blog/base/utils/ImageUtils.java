package com.eussi.blog.base.utils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author wangxm on 2019/1/31.
 */
@Slf4j
public class ImageUtils {

    /**
     * 是否可以写入文件
     * @param dest
     * @throws IOException
     */
    public static void validate(String dest) throws IOException {
        File destFile = new File(dest);
        if (destFile.getParentFile() != null && !destFile.getParentFile().exists() && !destFile.getParentFile().mkdirs()) {
            throw new IOException("Destination \'" + dest + "\' directory cannot be created");
        } else if (destFile.exists() && !destFile.canWrite()) {
            throw new IOException("Destination \'" + dest + "\' exists but is read-only");
        }
    }

    /**
     * 读文件
     * @param urlString
     * @return
     * @throws Exception
     */
    public static byte[] download(String urlString) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Thumbnails.of(new URL(urlString)).toOutputStream(output);
        return output.toByteArray();
    }

    /**
     * 图片压缩,各个边按比例压缩
     *
     * @param builder Thumbnails.of
     * @param width   压缩后的宽度
     * @param height  压缩后的高度
     * @param <T>     T
     * @throws java.io.IOException IOException
     */
    public static <T> byte[] scale(Thumbnails.Builder<T> builder, int width, int height) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        builder.size(width, height).toOutputStream(output);
        return output.toByteArray();
    }

    /**
     * 根据最大宽度图片压缩
     *
     * @param file    原图位置
     * @param maxSize 指定压缩后最大边长
     * @throws java.io.IOException IOException
     */
    public static byte[] scaleByWidth(MultipartFile file, int maxSize) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Thumbnails.of(file.getInputStream()).width(maxSize).toOutputStream(output);
        return output.toByteArray();
    }

    public static byte[] scale(File file, int width, int height) throws IOException {
        return scale(Thumbnails.of(file), width, height);
    }

    public static byte[] scale(File file, int maxSize) throws IOException {
        return scale(file, maxSize, maxSize);
    }

    public static byte[] scale(MultipartFile file, int width, int height) throws IOException {
        return scale(Thumbnails.of(file.getInputStream()), width, height);
    }

    public static byte[] scale(MultipartFile file, int maxSize) throws IOException {
        return scale(file, maxSize, maxSize);
    }

    /**
     * 截图
     *
     * @param builder  Thumbnails.of
     * @param position the position
     * @param width    width
     * @param height   height
     * @param <T>      T
     * @throws java.io.IOException          IOException
     * @throws InterruptedException InterruptedException
     */
    public static <T> byte[] screenshot(Thumbnails.Builder<T> builder, Position position, int width, int height) throws IOException, InterruptedException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        //keepAspectRatio(false) 默认是按照比例缩放的
        builder.size(width, height).sourceRegion(position, width, height).keepAspectRatio(false).toOutputStream(output);
        return output.toByteArray();
    }

    public static byte[] screenshot(File file, int x, int y, int width, int height) throws IOException, InterruptedException {
        return screenshot(Thumbnails.of(file), new Coordinate(x, y), width, height);
    }

    public static byte[] screenshot(File file, int x, int y, int size) throws IOException, InterruptedException {
        return screenshot(file, x, y, size, size);
    }

    public static byte[] screenshot(File file, int width, int height) throws IOException, InterruptedException {
        return screenshot(Thumbnails.of(file), Positions.CENTER, width, height);
    }

    public static byte[] screenshot(MultipartFile file, int x, int y, int width, int height) throws IOException, InterruptedException {
        return screenshot(Thumbnails.of(file.getInputStream()), new Coordinate(x, y), width, height);
    }

    public static byte[] screenshot(MultipartFile file, int x, int y, int size) throws IOException, InterruptedException {
        return screenshot(file, x, y, size, size);
    }

    public static byte[] screenshot(MultipartFile file, int width, int height) throws IOException, InterruptedException {
        return screenshot(Thumbnails.of(file.getInputStream()), Positions.CENTER, width, height);
    }

}
