package com.yxyk.user.bean.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author deng
 * @date 2020/6/16 0016
 */

@Data
public class AdminUserInfo {

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 管理员表业务主键
     */
    private String userId;

    /**
     * 账号状态 0.正常 1.异常
     */
    private Short status;

    /**
     * 创建人业务主键Id
     */
    private String createUserId;

    /**
     * 更新人业务主键Id
     */
    private String updateUserId;

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
