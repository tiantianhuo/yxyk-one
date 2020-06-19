package yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;
import yxyk.bean.po.AdminArticle;

import java.time.LocalDateTime;

public interface ArticleService extends BaseService {
    Page<AdminArticle> findAll(LocalDateTime startTime, LocalDateTime endTime, String keyword, Integer pageNum, Integer pageSize);
    void saveArticle(AdminArticle adminArticle) throws OperationException;
    AdminArticle findByIdAndStates(Long id);
    boolean delSensitive(AdminArticle adminArticle);
}
