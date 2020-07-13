package com.yxyk.fegin.navigation;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.vo.VoNavigation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Classname BannerController
 * @Description TODO
 * @Date 2020/6/17 15:26
 * @Created by cw
 */
@FeignClient(value = "menu")
public interface NavigationFeigen {

    /**
     * 查询栏目通过权限
     * @return list
     */
    @PostMapping("/navigation/findAll")
    JSONResponse findAll();
    /**
     * 保存
     * @param voNavigation vo
     */
    @PostMapping("/navigation/save")
    void saveNavigation(@RequestBody VoNavigation voNavigation);

    /**
     * 通过ID删除
     * @param voParams id
     */
    @PostMapping("/navigation/delete")
    void deleteById(VoParams voParams);

    /**
     * 通过id查询
     * @param id id
     * @return na
     */
    @PostMapping("/navigation/findOne")
    Navigation findOne(Long id);
}
