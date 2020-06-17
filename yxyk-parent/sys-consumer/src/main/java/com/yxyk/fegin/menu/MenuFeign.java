package com.yxyk.fegin.menu;


import com.yxyk.bean.common.JSONResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "menu")
public interface MenuFeign {

    @PostMapping("/banners/uploadBanner")
    public JSONResponse upload();
}
