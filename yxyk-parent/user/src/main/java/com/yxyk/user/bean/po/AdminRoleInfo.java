package com.yxyk.user.bean.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author deng
 * @date 2020/6/16 0016
 */
@Data
public class AdminRoleInfo {

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 账号状态(0.正常 1.异常)
     */
    private Short status;

    /**
     * 创建人业务主键
     */
    private String createUserId;

    /**
     * 更新人业务主键
     */
    private String updateUserId;

    /**
     * 角色表业务主键
     */
    private String roleId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;

}
