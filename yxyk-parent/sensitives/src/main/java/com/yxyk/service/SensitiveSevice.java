package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.AdminSensitive;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface SensitiveSevice extends BaseService{
    Page<AdminSensitive> findAll(LocalDateTime startTime, LocalDateTime endTime, String sensitiveword, Integer pageNum, Integer pageSize);
    void saveSensitive(AdminSensitive adminSensitive) throws OperationException;
    AdminSensitive findByIdAndStates(Long id);
    boolean delSensitive(AdminSensitive adminSensitive);
}