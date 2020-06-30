package com.yxyk.utils;


import com.yxyk.bean.po.AdminArticle;
import com.yxyk.bean.vo.VoArticle;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by zhaokai
 * Date: 2020/6/18
 * Time：11:49
 */
public class ArticleChangeUtils {
    public static AdminArticle changeToAr(VoArticle voArticle) {
        AdminArticle adminArticle = new AdminArticle();
        adminArticle.setSort(voArticle.getSort());
        adminArticle.setChannel(voArticle.getChannel());
        adminArticle.setTitle(voArticle.getTitle());
        adminArticle.setContent(voArticle.getContent());
        adminArticle.setCreatePerson("USER");
        return adminArticle;
    }
}
