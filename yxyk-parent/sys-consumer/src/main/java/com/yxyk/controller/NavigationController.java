package com.yxyk.controller;

import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.vo.VoNavigation;
import com.yxyk.fegin.navigation.NavigationFeigen;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Classname NavigationController
 * @Description TODO
 * @Date 2020/6/16 11:37
 * @Created by cw
 */
@RestController
@RequestMapping(value = "/apis/navigation/")
@AllArgsConstructor
public class NavigationController extends BaseController {


    private final NavigationFeigen navigationFeigen;


    /**
     * 查询栏目
     *
     * @return JSONResponse
     */
    @PostMapping("findAll")
    public JSONResponse findAllNavigation() {
        return this.success(navigationFeigen.findAll());
    }

    /**
     * 保存栏目
     *
     * @param voNavigation vo
     * @return JSONResponse
     */
    @PostMapping("save")
    public JSONResponse save(@Valid VoNavigation voNavigation) {
        navigationFeigen.saveNavigation(voNavigation);
        return this.success(SysConst.SUCCESS);
    }

    /**
     * 删除栏目
     *
     * @param voParams id
     * @return JSONResponse
     */
    @PostMapping("delete")
    public JSONResponse deleteById(VoParams voParams) {
        navigationFeigen.deleteById(voParams);
        return this.success(SysConst.DEL_SUCCESS);
    }

    /**
     * 通过id查询
     *
     * @param voParams vo
     * @return JSONResponse
     */
    @PostMapping("findOne")
    public JSONResponse findOne(VoParams voParams) {
        Navigation navigation = navigationFeigen.findOne(voParams.getId());
        return this.success(navigation);
    }

}
