//package com.yxyk.utils;
//
//import com.yxyk.properties.UploadProperties;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 发送httpRequest请求工具类
// *
// * @author fhx
// * @date 2019/9/2 14:49
// */
//@Component
//public class HttpRequestUtil {
//
//    @Autowired
//    private UploadProperties uploadProperties;
//
//    /**
//     * 图片上传
//     *
//     * @param url      请求url
//     * @param paramMap 请求参数
//     * @return String
//     */
//    private static String postRequest(String url, MultiValueMap<String, Object> paramMap) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject(url, paramMap, String.class);
//    }
//
//    /**
//     * 题库默认图片处理
//     *
//     * @param base64 图片base64
//     * @return String
//     */
//    public String saveDefaultImg(String base64) {
//        return getDownLoadUrl(base64,"default-img/");
//    }
//
//    /**
//     * 下载地址
//     * @param base64
//     * @return
//     */
//    public String saveDefaultFile(String base64,String doc){
//        return getDownLoadUrl(base64,uploadProperties.getDownUrl(),doc);
//    }
//
//    /**
//     * 批量上传
//     *
//     * @param baseStr baseCode集合
//     * @return List<String>
//     */
//    public List<String> saveImages(String... baseStr) {
//        return Arrays.stream(baseStr).map(this::saveDefaultImg).collect(Collectors.toList());
//    }
//    public String getDownLoadUrl(String base64,String downUrl){
//        if (!StringUtils.isBlank(base64)) {
//            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
//            ByteArrayResource bytes = Base64Util.base64ToByteArray(base64);
//            paramMap.add("filename", "default-img");
//            paramMap.add("instream", bytes);
//            String postRequest = HttpRequestUtil.postRequest(uploadProperties.getServiceUrl() + uploadProperties.getRequestUrl(), paramMap);
//            return uploadProperties.getServiceUrl() + uploadProperties.getTextUrl() + downUrl + postRequest;
//        } else {
//            return StringUtils.EMPTY;
//        }
//    }
//    public String getDownLoadUrl(String base64,String downUrl,String fileDoc){
//        if (!StringUtils.isBlank(base64)) {
//            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
//            ByteArrayResource bytes = Base64Util.base64ToByteArray(base64);
//            paramMap.add("filename", "default-img");
//            paramMap.add("instream", bytes);
//            String postRequest = HttpRequestUtil.postRequest(uploadProperties.getServiceUrl() + uploadProperties.getRequestUrl(), paramMap);
//            return uploadProperties.getServiceUrl() + uploadProperties.getTextUrl() + downUrl + postRequest+fileDoc;
//        } else {
//            return StringUtils.EMPTY;
//        }
//    }
//}
