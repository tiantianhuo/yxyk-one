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
     * 角色管理
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
     * 角色详情
     *
     * @return String
     */
    @GetMapping("addDetail")
    public String addDetail() {
        return "accountDetail/Detail";
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
     *
     * @return
     */
    @GetMapping("modular_table")
    public String modularTable() {
        return "modular/modular_table";
    }
    /**
     * 文章列表
     *
     * @return
     */
    @GetMapping("ArticleList")
    public String ArticleList() {
        return "article/ArticleList";
    }
    /**
     * 文章添加
     *
     * @return
     */
    @GetMapping("ArticleDetail")
    public String ArticleDetail() {
        return "article/ArticleDetail";
    }
    /**
     * 敏感ci列表
     *
     * @return
     */
    @GetMapping("SensititveList")
    public String SensititveList() {
        return "sensitive/SensititveList";
    }
    /**
     * 敏感ci添加
     *
     * @return
     */
    @GetMapping("SensitiveDetail")
    public String SensitiveDetail() {
        return "sensitive/SensitiveDetail.html";
    }
    /**
     * 敏感ci导入
     *
     * @return
     */
    @GetMapping("excellmport")
    public String excellmport() {
        return "sensitive/excellmport.html";
    }

}
