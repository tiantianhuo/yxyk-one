package com.yxyk.service.impl;


import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.AdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitiveAll;
import com.yxyk.reportory.SensitiveRepository;
import com.yxyk.service.SensitiveSevice;
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
import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
@Transactional(rollbackOn = RuntimeException.class)
public class SensitiveSeviceImpl implements SensitiveSevice {
    @Autowired
    private  SensitiveRepository sensitiveRepository;

    @Override
    public AdminSensitive save(AdminSensitive adminSensitive) {
        System.out.println("Sensitiveword:" + adminSensitive.getSensitiveword());
        return sensitiveRepository.save(adminSensitive);
    }

    @Override
    public void saveSensitive(VoAdminSensitive VoAdminSensitive) throws OperationException {
        AdminSensitive adminSensitive = VoChangeUtils.changeToSen(VoAdminSensitive);
        sensitiveRepository.save(adminSensitive);
    }

    @Override
    public boolean delSensitive(AdminSensitive adminSensitive) {
        adminSensitive.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
        sensitiveRepository.save(adminSensitive);
        return true;
    }

    @Override
    public Page<AdminSensitive> findAll(VoAdminSensitiveAll voAdminSensitive) {
        Map<String, SearchFilter> map = new HashMap<>();
        voAdminSensitive.setPageNum(1);
        voAdminSensitive.setPageSize(10);
        map.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        if (StringUtils.isNotBlank(voAdminSensitive.getStartTime()) && StringUtils.isNotBlank(voAdminSensitive.getEndTime())) {
            map.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, DateUtils.parseDateTime(voAdminSensitive.getStartTime())));
            map.put("endTime", new SearchFilter("createTime", SearchFilter.Operator.LTE, DateUtils.parseDateTime(voAdminSensitive.getEndTime())));
        }
        // 搜索关键词
       /* if (StringUtils.isNotEmpty(voAdminSensitive.getKeyword())) {
            map.put("title", new SearchFilter("title", SearchFilter.Operator.LIKE, voAdminSensitive.getKeyword()));
            map.put("content", new SearchFilter("content", SearchFilter.Operator.LIKE, voAdminSensitive.getKeyword()));
        }*/
        map.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        Specification<AdminSensitive> specification = DynamicSpecifications.bySearchFilter(map.values(), AdminSensitive.class);
        PageRequest of = PageRequest.of(voAdminSensitive.getPageNum(), voAdminSensitive.getPageSize(), new Sort(Sort.Direction.ASC, "sort"));
        //PageRequest of = PageRequest.of(1, 10, new Sort(Sort.Direction.ASC, "sort"));

        Page<AdminSensitive> page = sensitiveRepository.findAll(specification,of);
        return page;
    }


    @Override
    public AdminSensitive findByIdAndStates(Long id) {
        return sensitiveRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }

}
