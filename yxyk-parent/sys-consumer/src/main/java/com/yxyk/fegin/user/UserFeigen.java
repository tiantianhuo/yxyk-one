package com.yxyk.fegin.user;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.vo.VoUser;
import com.yxyk.bean.vo.VoUserSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Classname BannerController
 * @Description TODO
 * @Date 2020/7/4 15:26
 * @Created by cw
 */
@FeignClient(value = "menu")
public interface UserFeigen {


    /**
     * 保存或者修改用户
     *
     * @param voUser 用户参数
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("/apis/user/saveUser")
    JSONResponse saveUser(@RequestBody @Valid VoUser voUser);


    /**
     * 获取用户分页
     *
     * @param voUserSearch 用户检索
     * @return JSONResponse
     */
    @PostMapping("/apis/user/findUserPage")
    JSONResponse findUserPage(@RequestBody VoUserSearch voUserSearch);

    /**
     * 删除用户根据用户id
     *
     * @param id 用户主键id
     */
    @PostMapping("/apis/user/deleteById")
    JSONResponse deleteById(@RequestParam("id") Long id);

    /**
     * 获取用户信息根据主键id
     *
     * @param id 主键id
     */
    @PostMapping("/apis/user/findById")
    JSONResponse findById(@RequestParam("id") Long id);
}
