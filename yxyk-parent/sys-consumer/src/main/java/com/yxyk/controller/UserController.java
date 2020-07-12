package com.yxyk.controller;

import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.vo.VoUser;
import com.yxyk.bean.vo.VoUserSearch;
import com.yxyk.fegin.user.UserFeigen;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10s
 * Time：14:22
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/apis/user/")
public class UserController extends BaseController {

    private final UserFeigen userFeigen;


    /**
     * 保存或者修改用户
     *
     * @param voUser 用户参数
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("saveUser")
    public JSONResponse saveUser(@Valid VoUser voUser) throws OperationException {
        userFeigen.saveUser(voUser);
        return this.success();
    }

    /**
     * 用户登录
     *
     * @param userName 用户名称
     * @param passWord 用户密码
     * @return JSONResponse
     */
    @PostMapping("loginUser")
    public JSONResponse loginUser(String userName, String passWord) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord, Boolean.FALSE);
        subject.login(token);
        return this.success();
    }

    /**
     * 用户退出
     *
     * @return JSONResponse
     */
    @PostMapping("logout")
    public JSONResponse logoutUser() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return this.success();
    }

    /**
     * 获取用户分页
     *
     * @param voUserSearch 用户检索
     * @return JSONResponse
     */
    @PostMapping("findUserPage")
    public JSONResponse findUserPage(@RequestBody VoUserSearch voUserSearch) {
       ;
        return userFeigen.findUserPage(voUserSearch);
    }

    /**
     * 删除用户根据用户id
     *
     * @param id 用户主键id
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("deleteById")
    public JSONResponse deleteById(Long id) throws OperationException {
        userFeigen.deleteById(id);
        return this.success();
    }

    /**
     * 获取用户信息根据主键id
     *
     * @param id 主键id
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("findById")
    public JSONResponse findById(Long id) throws OperationException {
        return userFeigen.findById(id);
    }

}
