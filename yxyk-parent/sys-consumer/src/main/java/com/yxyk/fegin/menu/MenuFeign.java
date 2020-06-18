package com.yxyk.fegin.menu;


import com.yxyk.bean.po.Banner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "menu")
public interface MenuFeign {

    @PostMapping("/account/login/{username}/{password}/{type}")
    public Banner login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("type") String type);
}
