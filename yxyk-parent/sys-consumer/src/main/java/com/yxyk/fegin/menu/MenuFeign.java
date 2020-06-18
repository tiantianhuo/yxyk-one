package com.yxyk.fegin.menu;


import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.vo.VoBanner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "menu")
public interface MenuFeign {
    /**
     * 上传图片
     *
     * @return JSONResponse
     */
    @PostMapping("/banners/uploadBanner")
    JSONResponse upload();


    /**
     * 保存修改banner
     *
     * @param voBanner vo
     * @return JSONResponse
     */
    @PostMapping("/banners/save")
    JSONResponse save(VoBanner voBanner);

    /**
     * 删除
     *
     * @param voParams vo
     * @return JSONResponse
     */
    @PostMapping("/banners/deleteBannerById")
    JSONResponse deleteOne(VoParams voParams);

    /**
     * 操作（1、上移 2、下移 3、置顶 4、置底）
     * @param id id
     * @param event event 操作（1、上移 2、下移 3、置顶 4、置底）
     * @return JSONResponse
     */
    @PostMapping("/banners/changeBannerIndex")
    JSONResponse changBannerIndex();








}
