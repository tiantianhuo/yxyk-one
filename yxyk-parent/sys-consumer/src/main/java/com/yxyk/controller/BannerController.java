package com.yxyk.controller;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.vo.VoBanner;
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

    /**
     * 保存修改banner
     *
     * @param voBanner vo
     * @return JSONResponse
     */
    @PostMapping("save")
    public JSONResponse save(VoBanner voBanner) {
        return menuFeign.save(voBanner);
    }

    /**
     * 删除
     *
     * @param voParams id
     * @return JSONResponse
     */
    @PostMapping
    public JSONResponse deleteOne(VoParams voParams) {
        return menuFeign.deleteOne(voParams);
    }




}
