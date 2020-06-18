package com.yxyk.controller;


import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.AdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitiveAll;
import com.yxyk.service.SensitiveSevice;
import com.yxyk.utils.ChangeUtils;
import com.yxyk.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/apis/Sensitive/")
public class SensitiveController extends BaseController {
    @Autowired
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
    public JSONResponse saveSensitive(@Valid VoAdminSensitive vosensitive) throws OperationException {
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
        AdminSensitive adminSensitive = sensitiveSevice.findByIdAndStates(id);
        if (adminSensitive.getDeleteState() == SysConst.DeletedState.DELETE_STATE.getCode()) {
            return this.error("该数据已删除");
        }
        sensitiveSevice.delSensitive(adminSensitive);
        return this.success();
    }
    /**
     * 获取所有未删除数据
     *
     * @param voSensitive 敏感词参数
     * @return JSONResponse
     */
    @PostMapping("findAllSensitive")
    public JSONResponse findAllSensitive(VoAdminSensitiveAll voSensitive) {
        LocalDateTime startTime = DateUtils.parseDateTime(voSensitive.getStartTime());
        LocalDateTime endTime = DateUtils.parseDateTime(voSensitive.getEndTime());
        Page<AdminSensitive> all = sensitiveSevice.findAll(startTime, endTime, voSensitive.getSensitiveword(), voSensitive.getPageNum(), voSensitive.getPageSize());
        return this.success(all);

    }
}
