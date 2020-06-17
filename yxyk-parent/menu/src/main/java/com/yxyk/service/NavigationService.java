package com.yxyk.service;

import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.vo.VoNavigation;
import com.yxyk.bean.vo.VoNavigationPage;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Classname NavigationService
 * @Description TODO
 * @Date 2020/6/16 11:19
 * @Created by cw
 */
public interface NavigationService extends BaseService<Navigation,Long> {

    /**
     * 查询栏目通过权限
     * @param str 权限字符串
     * @return list
     */
    List<Navigation> findAllByAuthority(String str);

    /**
     * 保存
     * @param voNavigation vo
     */
    void saveNavigation(VoNavigation voNavigation);

    /**
     * 通过ID删除
     * @param voParams id
     */
    void deleteById(VoParams voParams);

    /**
     * 通过id查询
     * @param id id
     * @return na
     */
    Navigation findOne(Long id);

}
