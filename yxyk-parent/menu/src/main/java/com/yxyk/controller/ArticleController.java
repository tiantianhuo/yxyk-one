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
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/apis/Article/")
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
    @PostMapping("saveArticle")
    public JSONResponse saveSensitive(@RequestBody VoArticle voArticle) throws OperationException {
        articleService.saveArticle(ArticleChangeUtils.changeToAr(voArticle));
        return this.success();
    }
    /**
     *删除文章
     *
     * @param id 删除文章id
     * @return JSONResponse
     */
    @PostMapping("delArticle")
    public JSONResponse delArticle(Long id) {
        AdminArticle adminArticle = articleService.findByIdAndStates(id);
        if (adminArticle.getDeleteState() == SysConst.DeletedState.DELETE_STATE.getCode()) {
            return this.error("该数据已删除");
        }
        articleService.delSensitive(adminArticle);
        return this.success();
    }

    /**
     * 获取所有未删除文章
     *
     * @param voArticle 文章参数
     * @return JSONResponse
     */
    @PostMapping("findAllArticle")
    public JSONResponse findAllArticle(@RequestBody VoArticleAll voArticle) {
        LocalDateTime startTime = DateUtils.parseDateTime(voArticle.getStartTime());
        LocalDateTime endTime = DateUtils.parseDateTime(voArticle.getEndTime());
        Page<AdminArticle> all = articleService.findAll(startTime, endTime, voArticle.getKeyword(), voArticle.getPageNum(), voArticle.getPageSize());
        return this.success(all);

    }

    /**
     * 文章上下移动
     * @param request
     * @return
     */
    @RequestMapping(value = "/courseSortUpdate",method = RequestMethod.GET  )
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
