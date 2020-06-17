package com.yxyk.service.impl;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Banner;
import com.yxyk.reportory.BannerSysRepository;
import com.yxyk.service.BannerSysService;
import com.yxyk.utils.DynamicSpecifications;
import com.yxyk.utils.SearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Created with IntelliJ IDEA.
 *
 * @author zhanghao
 * date: 2019/10/10 16:43
 * description:
 */
@Service
@AllArgsConstructor
@Transactional(rollbackOn = RuntimeException.class)
public class BannerSysServiceImpl implements BannerSysService {

    private final BannerSysRepository bannerSysRepository;

    @Override
    public Page<Banner> findAllBanner(LocalDateTime startTime, LocalDateTime endTime, String name, int curr, int limit, Long pid) {
        Map<Object, SearchFilter> filters = new HashMap<>();
        if (!("".equals(name))) {
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, name));
        }
        filters.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        // 开始时间
        if (startTime != null) {
            filters.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, startTime));
        }
        // 结束时间
        if (endTime != null) {
            filters.put("endTime", new SearchFilter("updateTime", SearchFilter.Operator.LTE, endTime));
        }
        filters.put("pId", new SearchFilter("pId", SearchFilter.Operator.EQ, pid));
        Specification<Banner> specification = DynamicSpecifications.bySearchFilter(filters.values(), Banner.class);
        PageRequest of = PageRequest.of(curr - 1, limit, new Sort(Sort.Direction.ASC, "orderNumber"));
        return bannerSysRepository.findAll(specification, of);
    }

    @Override
    public Banner findBannerById(Long id) {
        return bannerSysRepository.findAllByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }

    @Override
    public void changeSortIndex(Long id, Integer event) throws OperationException {
        //当前的
        Banner one = bannerSysRepository.findAllByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
        Long procuratorId = one.getNavigationId();
        List<Banner> bannerSysList = getBannerSysList(procuratorId);
        int length = bannerSysList.size();
        if (length == 1) {
            throw new OperationException("只有一个无需进行操作!");
        }
        if (event.longValue() == SysConst.SortEvent.upwardEVENT.getCode()) {
            //上移
            for (int i = 0; i < length; i++) {
                if (bannerSysList.get(i).getId().longValue() == one.getId().longValue()) {
                    Banner beBannerSys = bannerSysList.get(i - 1);
                    changeLocation(one, beBannerSys);
                    return;
                }
            }

        } else if (event.longValue() == SysConst.SortEvent.downEVENT.getCode()) {
            //下移
            //获取当前的下一个
            for (int i = 0; i < length; i++) {
                if (bannerSysList.get(i).getId().longValue() == one.getId().longValue()) {
                    Banner beBannerSys = bannerSysList.get(i + 1);
                    changeLocation(one, beBannerSys);
                    return;
                }
            }
        } else if (event.longValue() == SysConst.SortEvent.bottomEVENT.getCode()) {
            //置底
            //获取最后一个的orderNumber
            if (length > 0) {
                Banner beBannerSys = bannerSysList.get(length - 1);
                changeLocation(one, beBannerSys);
            }

        } else if (event.longValue() == SysConst.SortEvent.roofEVENT.getCode()) {
            //置顶
            Banner beBannerSys = bannerSysList.get(0);
            changeLocation(one, beBannerSys);
        }
    }

    /***
     * 交换位置
     * @param one 当前的对象
     * @param beBannerSys 被交换的对象
     */
    private void changeLocation(Banner one, Banner beBannerSys) {
        long order = beBannerSys.getOrderNumber();
        long nowOrder = one.getOrderNumber();
        one.setOrderNumber(order);
        beBannerSys.setOrderNumber(nowOrder);
        bannerSysRepository.save(beBannerSys);
        bannerSysRepository.save(one);
    }


    /**
     * 公共方法获取公告list
     *
     * @param id procuratorId
     * @return List<BannerSys>
     */
    private List<Banner> getBannerSysList(Long id) {
        HashMap<String, SearchFilter> filterHashMap = new HashMap<>();
        filterHashMap.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        filterHashMap.put("procuratorId", new SearchFilter("procuratorId", SearchFilter.Operator.EQ, id));
        Specification<Banner> specification = DynamicSpecifications.bySearchFilter(filterHashMap.values(), Banner.class);
        return bannerSysRepository.findAll(specification, Sort.by("orderNumber"));
    }


    @Override
    public Banner save(Banner entity) {
        return bannerSysRepository.save(entity);
    }

    @Override
    public Optional<Banner> findById(Long aLong) {
        return bannerSysRepository.findById(aLong);
    }

}


