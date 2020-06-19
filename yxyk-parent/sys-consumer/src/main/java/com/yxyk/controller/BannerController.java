package com.yxyk.controller;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.vo.VoBanner;
import com.yxyk.bean.vo.VoBannerSys;
import com.yxyk.fegin.menu.MenuFeign;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public JSONResponse save(@RequestBody VoBanner voBanner) {
        return menuFeign.save(voBanner);
    }

    /**
     * 删除
     *
     * @param voParams id
     * @return JSONResponse
     */
    @PostMapping("deleteOne")
    public JSONResponse deleteOne(@RequestBody VoParams voParams) {
        return menuFeign.deleteOne(voParams);
    }


    /**
     * 操作（1、上移 2、下移 3、置顶 4、置底）
     *
     * @param id    id
     * @param event event 操作（1、上移 2、下移 3、置顶 4、置底）
     * @return JSONResponse
     */
    @PostMapping("changBannerIndex")
    public JSONResponse changBannerIndex( Long id, Integer event) {
        return menuFeign.changBannerIndex(id, event);
    }

    /**
     * 条件查询
     *
     * @param voBannerSys vo
     * @return JSONResponse
     */
    @RequestMapping("findAllBanner")
    public JSONResponse findAllBanner(@RequestBody VoBannerSys voBannerSys) {
        return menuFeign.findAllBanner(voBannerSys);
    }

    /**
     * 通过id查询
     *
     *
     * @param id id
     * @return JSONResponse
     */
    @PostMapping("findBannerById")
    public JSONResponse findBannerById(Long id) {
        return menuFeign.findBannerById(id);
    }
}
