package com.yxyk.controller;

import com.google.common.collect.Maps;
import com.yxyk.utils.HttpRequestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname BannerController
 * @Description TODO
 * @Date 2020/6/17 15:26
 * @Created by cw
 */
@RestController
@RequestMapping(value = "/apis/file/")
public class uploadController {

    /**
     * 上传文件
     * @param multipartFile file
     * @return String
     */
    @PostMapping("/upload")
    public String upload2(MultipartFile multipartFile) {
        return HttpRequestUtil.uploadFile("http://localhost:9280/fastdfs/filesystem/upload",multipartFile,"test", Maps.newHashMap(), Maps.newHashMap());
    }
}
