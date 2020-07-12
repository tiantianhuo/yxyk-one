package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.AdminArticle;
import com.yxyk.bean.po.Banner;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

public interface ArticleService extends BaseService<AdminArticle, Long> {
    Page<AdminArticle> findAll(VoArticleAll voArticle);
    void saveArticle(VoArticle voArticle) throws OperationException;
    AdminArticle findByIdAndStates(Long id);
    boolean delSensitive(AdminArticle adminArticle);
    AdminArticle findArticleById(Long id);
    void changeSortIndex(Long id, Integer event)throws OperationException;
}
