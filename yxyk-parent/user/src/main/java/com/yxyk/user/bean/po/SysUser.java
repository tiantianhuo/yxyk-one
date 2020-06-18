package com.yxyk.user.bean.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author deng
 * @date 2020/6/17 0017
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户表业务主键
     */
    private String userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 0.男 1.女
     */
    private Integer sex;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 账号状态 0.正常 1.异常
     */
    private Integer status;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 更新人ID
     */
    private String updateUserId;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 用户类型 0.APP用户 1.后台管理账户
     */
    private Integer userType;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;

}
