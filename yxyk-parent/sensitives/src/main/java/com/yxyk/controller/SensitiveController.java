package com.yxyk.controller;


import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Sensitive;
import com.yxyk.bean.vo.VoSensitive;
import com.yxyk.bean.vo.VoSensitiveAll;
import com.yxyk.controller.common.BaseController;
import com.yxyk.service.SensitiveSevice;
import com.yxyk.utils.ChangeUtils;
import com.yxyk.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/apis/Sensitive/")

public class SensitiveController extends BaseController {
    private final SensitiveSevice sensitiveSevice;

    public SensitiveController(SensitiveSevice sensitiveSevice) {
        this.sensitiveSevice = sensitiveSevice;
    }

    /**
     * 添加
     * @param vosensitive 敏感词参数
     * @return JSONResponse
     * @throws OperationException 自定义异常信息
     */
    @PostMapping("saveSensitive")
    public JSONResponse saveSensitive(@Valid VoSensitive vosensitive) throws OperationException {
       sensitiveSevice.saveSensitive(ChangeUtils.changeToSen(vosensitive));
        return this.success();
    }
    /**
     *删除敏感词
     *
     * @param id 删除敏感词id
     * @return JSONResponse
     */
    @PostMapping("delSensitive")
    public JSONResponse delSensitive(Long id) {
        Sensitive sensitive = sensitiveSevice.findByIdAndStates(id);
        if (sensitive.getStates() == SysConst.DeletedState.DELETE_STATE.getCode()) {
            return this.error("该数据已删除");
        }
        sensitiveSevice.delSensitive(sensitive);
        return this.success();
    }
    /**
     * 获取所有未删除数据
     *
     * @param voSensitive 敏感词参数
     * @return JSONResponse
     */
    @PostMapping("findAllSensitive")
    public JSONResponse findAllSensitive(VoSensitiveAll voSensitive) {
        LocalDateTime startTime = DateUtils.parseDateTime(voSensitive.getStartTime());
        LocalDateTime endTime = DateUtils.parseDateTime(voSensitive.getEndTime());
        Page<Sensitive> all = sensitiveSevice.findAll(startTime, endTime, voSensitive.getSensitiveword(), voSensitive.getPageNum(), voSensitive.getPageSize());
        return this.success(all);

    }
}
