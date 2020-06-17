package com.yxyk.controller;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.fegin.menu.MenuFeign;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname BannerController
 * @Description TODO
 * @Date 2020/6/17 15:26
 * @Created by cw
 */
@RestController
@RequestMapping(value = "/apis/banner/")
@AllArgsConstructor
public class BannerController {

    private final MenuFeign menuFeign;
    @PostMapping("nihao")
    public JSONResponse nihao(){
        return menuFeign.upload();
    }

}
