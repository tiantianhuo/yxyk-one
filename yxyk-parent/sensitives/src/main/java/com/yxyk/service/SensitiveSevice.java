package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.Sensitive;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface SensitiveSevice extends BaseService{
    Page<Sensitive> findAll(LocalDateTime startTime, LocalDateTime endTime, String sensitiveword, Integer pageNum, Integer pageSize);
    void saveSensitive(Sensitive sensitive) throws OperationException;
    Sensitive findByIdAndStates(Long id);
    boolean delSensitive(Sensitive sensitive);
}