package yxyk.service.impl;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Navigation;
import com.yxyk.service.NavigationService;
import com.yxyk.service.impl.NavigationServiceImpl;
import com.yxyk.utils.SearchFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yxyk.bean.po.AdminArticle;
import yxyk.repository.ArticleRepository;
import yxyk.service.ArticleService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleSeviceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Page<AdminArticle> findAll(LocalDateTime startTime, LocalDateTime endTime, String keyword, Integer pageNum, Integer pageSize) {
        Map<String, SearchFilter> map = new HashMap<>();
        map.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        // 结束时间
        if (endTime != null) {
            map.put("endTime", new SearchFilter("updateTime", SearchFilter.Operator.LTE, endTime));
        }
        // 开始时间
        if (startTime != null) {
            map.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, startTime));
        }
        // 搜索关键词
        if (StringUtils.isNotEmpty(keyword)) {
            map.put("title", new SearchFilter("title", SearchFilter.Operator.LIKE, keyword));
            map.put("content", new SearchFilter("content", SearchFilter.Operator.LIKE, keyword));
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageReq = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<AdminArticle> page = articleRepository.findAll(pageReq);

        if (page.getContent().size() != 0) {
            for (AdminArticle adminSensitive : page) {
                AdminArticle sensitives = articleRepository.findByIdAndDeleteState(adminSensitive.getId(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
                NavigationService navigationService = new NavigationServiceImpl();
                if(sensitives !=null){
                    Navigation navigation = navigationService.findOne(sensitives.getChannel());
                    adminSensitive.setTitle(sensitives.getTitle());
                    adminSensitive.setChannelName(navigation.getNavigationName());
                    adminSensitive.setCreateTime(sensitives.getCreateTime());
                    adminSensitive.setCreatePerson(sensitives.getCreatePerson());
                }else {
                    adminSensitive.setTitle("");
                }
            }
        }
        return page;
    }
    public Optional<AdminArticle> findById(Long id) {
        return articleRepository.findById(id);
    }
    @Override
    public void saveArticle(AdminArticle adminArticle) throws OperationException {
        Long senId = adminArticle.getId();
        if (senId != null && senId != 0L) {
            AdminArticle dbsensitive = this.findById(senId).orElseThrow(() -> new OperationException("数据不存在或者已经被删除"));

            if (Objects.equals(dbsensitive.getId(), senId)) {
                dbsensitive.setSort(adminArticle.getSort());
                dbsensitive.setTitle(adminArticle.getTitle());
                dbsensitive.setContent(adminArticle.getContent());
                dbsensitive.setUpdatePersn("PERSON");
                dbsensitive.setUpdateTime(LocalDateTime.now());
                articleRepository.save(dbsensitive);
            } else {
                throw new OperationException("数据称重复,换一个试试吧");
            }
        } else {
            AdminArticle sensitives = new AdminArticle();
            sensitives.setSort(adminArticle.getSort());
            sensitives.setTitle(adminArticle.getTitle());
            sensitives.setContent(adminArticle.getContent());
            sensitives.setCreateTime(LocalDateTime.now());
            sensitives.setCreatePerson("sta");
            articleRepository.save(sensitives);
        }
    }

    @Override
    public AdminArticle findByIdAndStates(Long id) {
        return articleRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }

    @Override
    public boolean delSensitive(AdminArticle adminArticle) {
        adminArticle.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
        articleRepository.save(adminArticle);
        return true;
    }
}
