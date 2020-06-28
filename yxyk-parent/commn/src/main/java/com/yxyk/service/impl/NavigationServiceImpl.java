package com.yxyk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.vo.VoNavigation;
import com.yxyk.reportory.NavigationRepository;
import com.yxyk.service.NavigationService;
import com.yxyk.utils.VoChangeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname NavigationServiceImpl
 * @Description TODO
 * @Date 2020/6/16 11:20
 * @Created by cw
 */

@Service
@Transactional(rollbackOn = RuntimeException.class)
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationRepository navigationRepository;

    @Override
    public List<Navigation> findAllByAuthority(String str) {
        List<String> permissionLists = JSONArray.parseArray(str, String.class);
        ArrayList<Navigation> returnList = new ArrayList<>();
        List<Navigation> navigations = navigationRepository.findByDeleteState(SysConst.DeletedState.UN_DELETE_STATE.getCode());
        for (Navigation navigation : navigations) {
            for (String permission : permissionLists) {
                if (navigation.getPermissionId().equals(permission)) {
                    returnList.add(navigation);
                }
            }
        }
        return returnList.stream().sorted(Comparator.comparing(Navigation::getSort)).collect(Collectors.toList());
    }

    @Override
    public void saveNavigation(VoNavigation voNavigation) {
        navigationRepository.save(VoChangeUtils.changeToNavigation(voNavigation));
    }

    @Override
    public void deleteById(VoParams voParams) {
        Navigation navigation = navigationRepository.findByIdAndDeleteState(voParams.getId(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
        navigation.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
        navigationRepository.save(navigation);
    }

    @Override
    public Navigation findOne(Long id) {
        return navigationRepository.findByIdAndDeleteState(id,SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }
}
