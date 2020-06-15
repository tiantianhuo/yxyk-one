package com.yxyk.config;

import com.yxyk.controller.common.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：16:07
 */
@Controller
public class MappingController extends BaseController {

    /**
     *添加文章
     */
    @GetMapping("addarticle")
    public String addarticle(){
        return "regulations/addarticle";
    }
    /**
    以案释法
     */
    @GetMapping("regulations/case")
    public String lawsAndRegulations(){
        return "regulations/case";
    }
    /**
    法律法规
    */
    @GetMapping("regulations")
    public String addArticle(){
        return "regulations/regulations";
    }
    /*添加文章*/
        @GetMapping("regulations/addarticle")
    public String addArticles(){
        return "regulations/addarticle";
    }

    /**
     * 无权限进入的页面
     */
    @GetMapping("nonePermission")
    public String nonePermission() {
        return "err/nonePermission";
    }

    /**
     * 登录页
     *
     * @return String
     */
    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    /**
     * 首页
     *
     * @return String
     */
    @GetMapping("office/workindex")
    public String workindex(ModelMap modelMap) {
        return "office/workindex";
    }


    /**
     * 登出
     *
     * @return String
     */
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "login/login";
    }

    /**
     * 首页
     *
     * @return String
     */
    @GetMapping("index")
    public String index(ModelMap modelMap) {
        return "newindextwo";
    }

    /**
     * 裁判文书
     *
     * @return String
     */
    @GetMapping("office/book")
    public String Book() {
        return "office/book";
    }

    /**
     * 裁判文书详情
     *
     * @return String
     */
    @GetMapping("office/off_app_dsofjd")
    public String offAppDsofjd() {
        return "office/off_app_dsofjd";
    }


    /*********************Setting********************/
    /**
     * 角色管理
     *
     * @return String
     */
    @GetMapping("PeiZhi/role")
    public String role() {
        return "peizhi/role";
    }

    /**
     * 个人中心
     *
     * @return String
     */
    @GetMapping("Setting/personal")
    public String personal() {
        return "Setting/personal";
    }

    /**
     * 用户管理
     *
     * @return String
     */
    @GetMapping("PeiZhi/user")
    public String user() {
        return "peizhi/user";
    }

    /**
     * 线索评估配置
     *
     * @return String
     */
    @GetMapping("PeiZhi/pingGu")
    public String pingGu() {
        return "peizhi/pinggu";
    }

    /**
     * @return 裁判文件
     */
    @GetMapping("book")//l路由
    public String book() {
        return "office/book";
    }//包

    /**
     * 趋势总览
     *
     * @return String
     */
    @GetMapping("trend")
    public String getTrend() {
        return "trend";
    }

    /**
     * 线索管理
     *
     * @return
     */
    @GetMapping("PopularOpinion/networklist")
    public String networklist() {
        return "PopularOpinion/networklist";
    }

    @GetMapping("PopularOpinion/networklist5")
    public String networklist5() {
        return "PopularOpinion/networklist555";
    }

    /**
     * 线索管理
     *
     * @return
     */
    @GetMapping("PopularOpinion/networklistDetails")
    public String networklistDetails() {
        return "clues_ver_tails/clues_ver_tails";
    }

    /**
     * 线索核查
     *
     * @return
     */
    @GetMapping("PopularOpinion/department")
    public String department() {
        return "PopularOpinion/department";
    }

    /**
     * 线索核查详情
     *
     * @return
     */
    @GetMapping("PopularOpinion/departmentDetails")
    public String departmentDetails() {
        return "clues_ver_tails/clues_pg_tails";
    }

    /***
     * 首页设置
     * @param liNumber 显示第几个li的table
     * @param modelMap
     * @return
     */
    @GetMapping("Index/index")
    public String index(Long liNumber, ModelMap modelMap) {
        modelMap.put("liNumber", liNumber);
        return "index/index";
    }

    /***
     * banner设置
     */
    @GetMapping("Index/banner")
    public String banner(Long id, ModelMap modelMap) {
        modelMap.put("id", id);
        return "index/banner";
    }

    /***
     * banner添加页面
     */
    @GetMapping("Index/addBanner")
    public String addBanner() {
        return "index/addBanner";
    }

    /***
     * 个人中心
     */
    @GetMapping("Setting/personalAccset")
    public String personalAccset(ModelMap modelMap) {
        String homeUrl = getUser().getHomeUrl();
        Integer adminState = getUser().getAdminState();
        modelMap.put("adminState", Objects.equals(adminState, 1));
        if (StringUtils.isEmpty(homeUrl)) {
            modelMap.put("homeUrl", "none");
        } else {
            modelMap.put("homeUrl", getUser().getHomeUrl());
        }

        return "Setting/personal_accset";
    }

    /**
     * 舆情首页
     *
     * @return
     */
    @GetMapping("yuqing/yuqingIndex")
    public String yuqing() {
        return "yuqing/yuqing";
    }

    /***
     * 公告查看
     */
    @GetMapping("Index/gongGao")
    public String affiche(Long id, ModelMap modelMap) {
        modelMap.put("id", id);
        return "index/gonggao";
    }

    /***
     * 公告添加
     */
    @GetMapping("Index/addAffiche")
    public String addAffiche(Long id, ModelMap modelMap) {
        modelMap.put("id", id);
        return "index/addAffiche";
    }
    /**
     * 我的上传
     *
     * @return
     */
    @GetMapping("yuqing/myupload")
    public String myupload() {
        return "yuqing/my-upload";
    }

    /**
     * 回收站列表页
     *
     * @return String
     */
    @GetMapping("office/recovery/recovery")
    public String recovery() {
        return  "recovery/recovery";
    }
    @GetMapping("demo")
    public String demo(){
        return "demo";
    }

    @GetMapping("robot")
    public String robot(Long liNumber, ModelMap modelMap){
        modelMap.put("liNumber", liNumber);
        return "index/indexTwo";
    }

}
