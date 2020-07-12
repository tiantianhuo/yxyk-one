package com.yxyk.controller;

import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.AdminArticle;
import com.yxyk.bean.po.Banner;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import com.yxyk.service.ArticleService;
import com.yxyk.utils.ArticleChangeUtils;
import com.yxyk.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private final ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    /**
     * 添加/修改
     * @param voArticle 文章参数
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("/saveArticle")
    public JSONResponse saveSensitive(@RequestBody VoArticle voArticle) throws OperationException {
         articleService.saveArticle(voArticle);
        return this.success();
    }
    /**
     *删除文章
     *
     * @param id 删除文章id
     * @return JSONResponse
     */
    @PostMapping("/delArticle")
    public JSONResponse delArticle(@RequestParam("id")Long id) {
        Optional<AdminArticle> adminArticle = articleService.findById(id);
        adminArticle.ifPresent(bannerSys -> {
            bannerSys.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
            articleService.save(bannerSys);
        });
        return this.success();
    }

    /**
     * 获取所有未删除文章
     *
     * @param voArticle 文章参数
     * @return JSONResponse
     */
    @PostMapping(value ="/findAllArticle")
    public JSONResponse findAllArticle(@RequestBody VoArticleAll voArticle) {
        Page<AdminArticle> all = articleService.findAll(voArticle);
        return this.success(all);

    }

    /**
     * 文章上下移动
     * @param request
     * @return
     */
    /*@RequestMapping(value = "/courseSortUpdate",method = RequestMethod.GET  )
    public JSONResponse courseSortUpdate(HttpServletRequest request){
        String sort=request.getParameter("sort");
        String sort1=sort.substring(0,sort.length()-1);
        AdminArticle   entity=articleService.findBySort(Long.parseLong(sort1));
        if(sort.substring(sort.length()-1).equals("1")){
            AdminArticle entity1=articleService.sortUp(Long.parseLong(sort1));
            long Sort = entity1.getSort();
            articleService.updateBySort(Sort,entity.getId());
            articleService.updateBySort(Long.parseLong(sort1),entity1.getId());
        }
        if(sort.substring(sort.length()-1).equals("2")){
            AdminArticle entity2=articleService.sortDown(Long.parseLong(sort1));
            long Sort = entity2.getSort();
            articleService.updateBySort(Sort,entity.getId());
            articleService.updateBySort(Long.parseLong(sort1),entity2.getId());
        }
        return this.success();
    }*/
    /**
     * 文章上移、下移、置顶、置底
     * 更改文章的排序位置
     *
     * @param id    banner 的id
     * @param event 操作（1、上移 2、下移 3、置顶 4、置底）
     * @return json
     */
    @PostMapping(value = "courseSortUpdate")
    public JSONResponse courseSortUpdate(@RequestParam("id") Long id, @RequestParam("event") Integer event) throws OperationException {
        articleService.changeSortIndex(id, event);
        return this.success();
    }


    /**
     * 修改回显:通过id查询
     *
     * @param id 请求参数
     * @return Result
     */
    @PostMapping(value = "findArticleById")
    public JSONResponse findArticleById(Long id) {
        AdminArticle article = articleService.findArticleById(id);
        return this.success(article);
    }

}
