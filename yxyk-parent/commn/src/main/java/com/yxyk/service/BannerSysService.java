package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.Banner;
import com.yxyk.bean.vo.VoBanner;
import com.yxyk.bean.vo.VoBannerSys;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhanghao
 * date: 2019/10/10 16:43
 * description:
 */
public interface BannerSysService extends BaseService<Banner, Long> {

    Page<Banner> findAllBanner(VoBannerSys voBannerSys);

    Banner findBannerById(Long id);

    void changeSortIndex(Long id, Integer event) throws OperationException;

    /**
     * 保存修改banner
     * @param voBanner voBanner
     */

    void saveBanner(VoBanner voBanner);
}
