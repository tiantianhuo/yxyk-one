package com.yxyk.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * base64工具类
 *
 * @author fhx
 * @date 2019/8/30 16:08
 */
@Component
public class Base64Util {

    /**
     * base64转图片存到本地
     *
     * @param base64      base64码
     * @param imgFilePath 保存地址加文件名
     * @return 文件名称
     */
    public static String base64ToImage(String base64, String imgFilePath) {
        if (base64 == null) // 图像数据为空
            return "";
        UUID uuid = UUID.randomUUID();
        String[] split = base64.split(",");
        String suffix = split[0].substring(split[0].indexOf("/") + 1, split[0].indexOf(";"));
        String fileName = imgFilePath + uuid + "." + suffix;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(split[1]);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(fileName);
            out.write(bytes);
            out.flush();
            out.close();
            return fileName;
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * base64转图片
     *
     * @param base64 base64码
     * @return byte数组流
     */
    public static ByteArrayResource base64ToByteArray(String base64) {
        if (StringUtils.isBlank(base64)) return null;
        String[] split = base64.split(",");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(split[1]);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            return new ByteArrayResource(bytes) {
                @Override
                public String getFilename() {
                    return "yxyk-path";
                }
            };
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 图片转base64
     * @param file
     * @return
     * @throws Exception
     */
    public static String parseBase64(MultipartFile file) throws Exception{
        BASE64Encoder base64Encoder =new BASE64Encoder();
        return file.getOriginalFilename()+","+ base64Encoder.encode(file.getBytes());
    }
}
