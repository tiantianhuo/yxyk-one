package com.yxyk.fegin.user;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoUser;
import com.yxyk.bean.vo.VoUserSearch;
import com.yxyk.utils.VoChangeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Classname BannerController
 * @Description TODO
 * @Date 2020/7/4 15:26
 * @Created by cw
 */
@FeignClient(value = "menu")
public interface userFeigen {


    /**
     * 保存或者修改用户
     *
     * @param voUser 用户参数
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("saveUser")
    public JSONResponse saveUser(@Valid VoUser voUser);





    /**
     * 获取用户分页
     *
     * @param voUserSearch 用户检索
     * @return JSONResponse
     */
    @PostMapping("findUserPage")
    public JSONResponse findUserPage(@RequestBody VoUserSearch voUserSearch);

    /**
     * 删除用户根据用户id
     *
     * @param id 用户主键id
     */
    @PostMapping("deleteById")
    public JSONResponse deleteById(Long id);

    /**
     * 获取用户信息根据主键id
     *
     * @param id 主键id
     */
    @PostMapping("findById")
    public JSONResponse findById(Long id);
}
