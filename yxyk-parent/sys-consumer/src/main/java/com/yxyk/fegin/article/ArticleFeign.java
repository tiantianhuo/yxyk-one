package com.yxyk.fegin.article;


import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.po.AdminArticle;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import com.yxyk.bean.vo.VoBanner;
import com.yxyk.bean.vo.VoBannerSys;
import com.yxyk.utils.DateUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(value = "article")
public interface ArticleFeign {

    /**
     * 保存修改article
     *
     * @param voArticle vo
     * @return JSONResponse
     */
    @PostMapping("/article/saveArticle")
        JSONResponse saveArticle(VoArticle voArticle);

    /**
     * 删除
     *
     * @param id 删除文章id
     * @return JSONResponse
     */
    @PostMapping("/article/delArticle")
    JSONResponse delArticle(Long id);

    /**
     * 获取所有未删除文章
     *
     * @param voArticle 文章参数
     * @return JSONResponse
     */
    @PostMapping("/article/findAllArticle")
   JSONResponse findAllArticle(@RequestBody VoArticleAll voArticle);

    /**
     * 操作（1、上移 2、下移 3、置顶 4、置底）
     *
     * @param id    id
     * @param event event 操作（1、上移 2、下移 3、置顶 4、置底）
     * @return JSONResponse
     */
    @PostMapping("/article/changeBannerIndex")
    JSONResponse changBannerIndex(@RequestParam(value = "id") Long id,@RequestParam("event") Integer event);

    /**
     * 通过id查询
     * @param id id
     * @return JSONResponse
     */
    @PostMapping("/article/findArticleById")
    JSONResponse findArticleById(Long id);
}