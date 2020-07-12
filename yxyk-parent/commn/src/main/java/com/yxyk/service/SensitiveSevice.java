package com.yxyk.service;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.po.AdminArticle;
import com.yxyk.bean.po.AdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitiveAll;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import com.yxyk.service.common.BaseService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface SensitiveSevice extends BaseService<AdminSensitive, Long>{
    void saveSensitive(VoAdminSensitive VoAdminSensitive) throws OperationException;
    boolean delSensitive(AdminSensitive adminSensitive);
    AdminSensitive findByIdAndStates(Long id);
    Page<AdminSensitive> findAll(VoAdminSensitiveAll voAdminSensitive);
}