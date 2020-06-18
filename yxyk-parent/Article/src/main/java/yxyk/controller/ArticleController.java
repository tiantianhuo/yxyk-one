package yxyk.controller;

import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yxyk.bean.po.AdminArticle;
import yxyk.bean.vo.VoArticle;
import yxyk.bean.vo.VoArticleAll;
import yxyk.service.ArticleService;
import yxyk.utils.ArticleChangeUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/apis/Article/")
public class ArticleController extends BaseController {
    @Autowired
    private final ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    /**
     * 添加
     * @param voArticle 文章参数
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("saveArticle")
    public JSONResponse saveSensitive(@Valid VoArticle voArticle) throws OperationException {
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
    public JSONResponse findAllArticle(VoArticleAll voArticle) {
        LocalDateTime startTime = DateUtils.parseDateTime(voArticle.getStartTime());
        LocalDateTime endTime = DateUtils.parseDateTime(voArticle.getEndTime());
        Page<AdminArticle> all = articleService.findAll(startTime, endTime, voArticle.getKeyword(), voArticle.getPageNum(), voArticle.getPageSize());
        return this.success(all);

    }
}
