package com.yxyk.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 全局路由控制
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/11
 * Time：20:26
 */
@Controller
public class MappingController {

    /**
     * 登录
     *
     * @return String
     */
    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    /**
     * 登出
     *
     * @return String
     */
    @GetMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "login/login";
    }

    /**
     * 机构管理
     *
     * @return String
     */
    @GetMapping("companyManger")
    public String companyManger() {
        return "companyManger/companyManger";
    }

    /**
     * 地域管理
     *
     * @return String
     */
    @GetMapping("regionManger")
    public String regionManger() {
        return "regionManger/regionManger";
    }

    /**
     * 账号管理
     *
     * @return String
     */
    @GetMapping("accountManger")
    public String accountManger() {
        return "accountManger/accountManger";
    }

    /**
     * 账号详情
     *
     * @return String
     */
    @GetMapping("accountDetail")
    public String accountDetail() {
        return "accountDetail/accountDetail";
    }

    /**
     * 举报模块管理
     * @return
     */
    @GetMapping("modular_table")
    public String modularTable(){
        return "modular/modular_table";
    }
}