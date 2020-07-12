package com.yxyk.service.impl;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.AdminArticle;
import com.yxyk.bean.po.Banner;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import com.yxyk.reportory.ArticleRepository;
import com.yxyk.service.ArticleService;
import com.yxyk.service.NavigationService;
import com.yxyk.utils.DateUtils;
import com.yxyk.utils.DynamicSpecifications;
import com.yxyk.utils.SearchFilter;
import com.yxyk.utils.VoChangeUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = RuntimeException.class)
public class ArticleSeviceImpl implements ArticleService {


    private final ArticleRepository articleRepository;
    @Override
    public Page<AdminArticle> findAll(VoArticleAll voArticle) {
        Map<String, SearchFilter> map = new HashMap<>();
        map.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        if (StringUtils.isNotBlank(voArticle.getStartTime()) && StringUtils.isNotBlank(voArticle.getEndTime())) {
            map.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, DateUtils.parseDateTime(voArticle.getStartTime())));
            map.put("endTime", new SearchFilter("createTime", SearchFilter.Operator.LTE, DateUtils.parseDateTime(voArticle.getEndTime())));
        }
        // 搜索关键词
        if (StringUtils.isNotEmpty(voArticle.getKeyword())) {
            map.put("title", new SearchFilter("title", SearchFilter.Operator.LIKE, voArticle.getKeyword()));
            map.put("content", new SearchFilter("content", SearchFilter.Operator.LIKE, voArticle.getKeyword()));
        }
        map.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        Specification<AdminArticle> specification = DynamicSpecifications.bySearchFilter(map.values(), AdminArticle.class);
        PageRequest of = PageRequest.of(voArticle.getPageNum(), voArticle.getPageSize(), new Sort(Sort.Direction.ASC, "sort"));
        Page<AdminArticle> page = articleRepository.findAll(specification,of);
        return page;
    }
    public Optional<AdminArticle> findById(Long id) {
        return articleRepository.findById(id);
    }

    /**
     * 保存
     * @param voArticle
     * @throws OperationException
     */
    @Override
    public void saveArticle(VoArticle voArticle) throws OperationException {
        AdminArticle adminArticle = VoChangeUtils.changeToAr(voArticle);
        articleRepository.save(adminArticle);
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
    @Override
    public AdminArticle findArticleById(Long id) {
        return articleRepository.findAllByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }
    @Override
    public void changeSortIndex(Long id, Integer event) throws OperationException {
        //当前的
        AdminArticle adminArticle = articleRepository.findAllByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
        Long procuratorId = adminArticle.getChannel();
        List<AdminArticle> adminArticleList = getArticleList(procuratorId);
        int length = adminArticleList.size();
        if (length == 1) {
            throw new OperationException("只有一个无需进行操作!");
        }
        if (event.longValue() == SysConst.SortEvent.upwardEVENT.getCode()) {
            //上移
            for (int i = 0; i < length; i++) {
                if (adminArticleList.get(i).getId().longValue() == adminArticle.getId().longValue()) {
                    AdminArticle beBannerSys = adminArticleList.get(i - 1);
                    changeLocation(adminArticle, beBannerSys);
                    return;
                }
            }

        } else if (event.longValue() == SysConst.SortEvent.downEVENT.getCode()) {
            //下移
            //获取当前的下一个
            for (int i = 0; i < length; i++) {
                if (adminArticleList.get(i).getId().longValue() == adminArticle.getId().longValue()) {
                    AdminArticle beBannerSys = adminArticleList.get(i + 1);
                    changeLocation(adminArticle, beBannerSys);
                    return;
                }
            }
        } else if (event.longValue() == SysConst.SortEvent.bottomEVENT.getCode()) {
            //置底
            //获取最后一个的orderNumber
            if (length > 0) {
                AdminArticle beBannerSys = adminArticleList.get(length - 1);
                changeLocation(adminArticle, beBannerSys);
            }

        } else if (event.longValue() == SysConst.SortEvent.roofEVENT.getCode()) {
            //置顶
            AdminArticle beBannerSys = adminArticleList.get(0);
            changeLocation(adminArticle, beBannerSys);
        }
    }
    /***
     * 交换位置
     * @param one 当前的对象
     * @param beBannerSys 被交换的对象
     */
    private void changeLocation(AdminArticle one, AdminArticle beBannerSys) {
        long order = beBannerSys.getSort();
        long nowOrder = one.getSort();
        one.setSort(order);
        beBannerSys.setSort(nowOrder);
        articleRepository.save(beBannerSys);
        articleRepository.save(one);
    }
    private List<AdminArticle> getArticleList(Long id) {
        HashMap<String, SearchFilter> filterHashMap = new HashMap<>();
        filterHashMap.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        filterHashMap.put("procuratorId", new SearchFilter("procuratorId", SearchFilter.Operator.EQ, id));
        Specification<AdminArticle> specification = DynamicSpecifications.bySearchFilter(filterHashMap.values(), AdminArticle.class);
        return articleRepository.findAll(specification, Sort.by("sort"));
    }

  /*  @Override
    public AdminArticle findBySort(Long sort) {
        return  articleRepository.findBySort(sort);
    }

    @Override
    public AdminArticle sortUp(Long sort) {
        return  articleRepository.ArticleBySortUp(sort);
    }

    @Override
    public void updateBySort(Long sort, Long id) {
        articleRepository.updateBySort(sort,id);
    }

    @Override
    public AdminArticle sortDown(Long sort) {
        return articleRepository.ArticleBySortDown(sort);
    }*/
  @Override
  public AdminArticle save(AdminArticle adminArticle) {
      return articleRepository.save(adminArticle);
  }


}
