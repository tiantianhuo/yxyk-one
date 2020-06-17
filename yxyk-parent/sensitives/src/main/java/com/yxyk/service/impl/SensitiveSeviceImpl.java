package com.yxyk.service.impl;


import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Sensitive;
import com.yxyk.repository.SensitiveRepository;
import com.yxyk.service.SensitiveSevice;
import com.yxyk.utils.DynamicSpecifications;
import com.yxyk.utils.SearchFilter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
@AllArgsConstructor
public class SensitiveSeviceImpl implements SensitiveSevice {
    private final SensitiveRepository sensitiveRepository;
    @Override
    public void saveSensitive(Sensitive sensitive) throws OperationException {
        Long senId = sensitive.getId();
        if (senId != null && senId != 0L) {
            Sensitive dbsensitive = this.findById(senId).orElseThrow(() -> new OperationException("数据不存在或者已经被删除"));;

            if (Objects.equals(dbsensitive.getId(), senId)) {
                dbsensitive.setSensitiveword(sensitive.getSensitiveword());
                dbsensitive.setUpdateTime(LocalDateTime.now());
                dbsensitive.setCreatePerson("sta");
                sensitiveRepository.save(dbsensitive);
            } else {
                throw new OperationException("数据称重复,换一个试试吧");
            }
        } else {
            Sensitive sensitives = new Sensitive();
            sensitives.setSensitiveword(sensitive.getSensitiveword());
            sensitives.setCreateTime(LocalDateTime.now());
            sensitives.setCreatePerson("sta");
            sensitiveRepository.save(sensitives);
        }
    }
    @Override
    public Sensitive findByIdAndStates(Long  id) {
        return sensitiveRepository.findByIdAndStates(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
    }

    public Optional<Sensitive> findById(Long id) {
        return sensitiveRepository.findById(id);
    }

    @Override
    public boolean delSensitive(Sensitive sensitive) {
        sensitive.setStates(SysConst.DeletedState.DELETE_STATE.getCode());
        sensitiveRepository.save(sensitive);
        return true;
    }
    @Override
    public Page<Sensitive> findAll(LocalDateTime startTime, LocalDateTime endTime, String sensitiveword,  Integer pageNum, Integer pageSize) {
        Map<String, SearchFilter> map = new HashMap<>();
        map.put("states", new SearchFilter("states", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        // 结束时间
        if (endTime != null) {
            map.put("endTime", new SearchFilter("updateTime", SearchFilter.Operator.LTE, endTime));
        }
        // 开始时间
        if (startTime != null) {
            map.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, startTime));
        }
        // 用户名
        if (StringUtils.isNotEmpty(sensitiveword)) {
            map.put("sensitiveword", new SearchFilter("sensitiveword", SearchFilter.Operator.LIKE, sensitiveword));
        }

        Specification<Sensitive> specification = DynamicSpecifications.bySearchFilter(map.values(), Sensitive.class);
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);

        Page<Sensitive> page = sensitiveRepository.findAll(specification, pageRequest);

        if (page.getContent().size() != 0) {
            for (Sensitive sensitive : page) {
                Sensitive sensitives = sensitiveRepository.findByIdAndStates(sensitive.getId(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
                if(sensitive!=null){
                    sensitive.setSensitiveword(sensitives.getSensitiveword());
                }else {
                    sensitive.setSensitiveword("");
                }
            }
        }
        return page;
    }

}
