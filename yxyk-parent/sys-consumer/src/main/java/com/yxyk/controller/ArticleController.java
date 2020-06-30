package com.yxyk.controller;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import com.yxyk.bean.vo.VoBanner;
import com.yxyk.bean.vo.VoBannerSys;
import com.yxyk.fegin.article.ArticleFeign;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ArticleController
 * @Description TODO
 * @Date 2020/6/17 15:26
 * @Created by zk
 */
@RestController
@RequestMapping(value = "/apis/article/")
@AllArgsConstructor
public class ArticleController {

    private final ArticleFeign articleFeign;

    /**
     * 保存修改article
     *
     * @param voArticle vo
     * @return JSONResponse
     */
    @PostMapping("saveArticle")
    public JSONResponse saveArticle(@RequestBody VoArticle voArticle) {
        return articleFeign.saveArticle(voArticle);
    }

    /**
     * 删除
     *
     * @param  id
     * @return JSONResponse
     */
    @PostMapping("delArticle")
    public JSONResponse delArticle(Long id) {
        return articleFeign.delArticle(id);
    }


    /**
     * 操作（1、上移 2、下移 3、置顶 4、置底）
     *
     * @param id    id
     * @param event event 操作（1、上移 2、下移 3、置顶 4、置底）
     * @return JSONResponse
     */
    @PostMapping("changBannerIndex")
    public JSONResponse changBannerIndex( Long id, Integer event) {
        return articleFeign.changBannerIndex(id, event);
    }

    /**
     * 条件查询
     *
     * @param voArticle vo
     * @return JSONResponse
     */
    @RequestMapping("findAllArticle")
    public JSONResponse findAllArticle(@RequestBody VoArticleAll voArticle) {
        return articleFeign.findAllArticle(voArticle);
    }

    /**
     * 通过id查询
     *
     *
     * @param id id
     * @return JSONResponse
     */
    @PostMapping("findArticleById")
    public JSONResponse findArticleById(Long id) {
        return articleFeign.findArticleById(id);
    }
}
