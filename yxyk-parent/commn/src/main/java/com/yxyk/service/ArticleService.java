package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.AdminArticle;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

public interface ArticleService extends BaseService {
    Page<AdminArticle> findAll(LocalDateTime startTime, LocalDateTime endTime, String keyword, Integer pageNum, Integer pageSize);
    void saveArticle(AdminArticle adminArticle) throws OperationException;
    AdminArticle findByIdAndStates(Long id);
    boolean delSensitive(AdminArticle adminArticle);
    AdminArticle findBySort(Long sort);
    AdminArticle sortUp(Long sort);
    void updateBySort(Long sort, Long id);
    AdminArticle sortDown(Long sort);
}
