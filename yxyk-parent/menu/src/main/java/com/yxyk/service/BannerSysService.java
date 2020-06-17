package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.Banner;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhanghao
 * date: 2019/10/10 16:43
 * description:
 */
public interface BannerSysService extends BaseService<Banner, Long> {

    Page<Banner> findAllBanner(LocalDateTime startTime, LocalDateTime endTime, String procuratorateName, int curr, int limit, Long procuratorId, Long pid);

    Banner findBannerById(Long id);

    void changeSortIndex(Long id, Integer event) throws OperationException;




}
