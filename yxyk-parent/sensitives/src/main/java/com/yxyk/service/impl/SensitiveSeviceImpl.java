package com.yxyk.service.impl;


import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.AdminSensitive;
import com.yxyk.repository.SensitiveRepository;
import com.yxyk.service.SensitiveSevice;
import com.yxyk.utils.DynamicSpecifications;
import com.yxyk.utils.SearchFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class SensitiveSeviceImpl implements SensitiveSevice {

    @Autowired
    private SensitiveRepository sensitiveRepository;

    @Override
    public void saveSensitive(AdminSensitive adminSensitive) throws OperationException {
        Long senId = adminSensitive.getId();
        if (senId != null && senId != 0L) {
            AdminSensitive dbsensitive = this.findById(senId).orElseThrow(() -> new OperationException("数据不存在或者已经被删除"));;

            if (Objects.equals(dbsensitive.getId(), senId)) {
                dbsensitive.setSensitiveword(adminSensitive.getSensitiveword());
                dbsensitive.setUpdateTime(LocalDateTime.now());
                dbsensitive.setCreatePerson("sta");
                sensitiveRepository.save(dbsensitive);
            } else {
                throw new OperationException("数据称重复,换一个试试吧");
            }
        } else {
            AdminSensitive sensitives = new AdminSensitive();
            sensitives.setSensitiveword(adminSensitive.getSensitiveword());
            sensitives.setCreateTime(LocalDateTime.now());
            sensitives.setCreatePerson("sta");
            sensitiveRepository.save(sensitives);
        }
    }
    @Override
    public AdminSensitive findByIdAndStates(Long  id) {
        return sensitiveRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }

    public Optional<AdminSensitive> findById(Long id) {
        return sensitiveRepository.findById(id);
    }

    @Override
    public boolean delSensitive(AdminSensitive adminSensitive) {
        adminSensitive.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
        sensitiveRepository.save(adminSensitive);
        return true;
    }
    @Override
    public Page<AdminSensitive> findAll(LocalDateTime startTime, LocalDateTime endTime, String sensitiveword, Integer pageNum, Integer pageSize) {
        Map<String, SearchFilter> map = new HashMap<>();
        map.put("deletestate", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        // 结束时间
        if (endTime != null) {
            map.put("endTime", new SearchFilter("updateTime", SearchFilter.Operator.LTE, endTime));
        }
        // 开始时间
        if (startTime != null) {
            map.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, startTime));
        }
        // 敏感词
        if (StringUtils.isNotEmpty(sensitiveword)) {
            map.put("sensitiveword", new SearchFilter("sensitiveword", SearchFilter.Operator.LIKE, sensitiveword));
        }

        Specification<AdminSensitive> specification = DynamicSpecifications.bySearchFilter(map.values(), AdminSensitive.class);
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);

        Page<AdminSensitive> page = sensitiveRepository.findAll(specification, pageRequest);

        if (page.getContent().size() != 0) {
            for (AdminSensitive adminSensitive : page) {
                AdminSensitive sensitives = sensitiveRepository.findByIdAndDeleteState(adminSensitive.getId(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
                if(sensitives !=null){
                    adminSensitive.setSensitiveword(sensitives.getSensitiveword());
                    adminSensitive.setCreateTime(sensitives.getCreateTime());
                    adminSensitive.setCreatePerson(sensitives.getCreatePerson());
                    adminSensitive.setUpdateTime(sensitives.getUpdateTime());
                    adminSensitive.setUpdataPerson(sensitives.getUpdataPerson());
                }else {
                    adminSensitive.setSensitiveword("");
                }
            }
        }
        return page;
    }

}
