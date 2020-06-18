package com.yxyk.user.bean.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author deng
 * @date 2020/6/16 0016
 */

@Data
@Entity
@Table(name = "app_user_info")
public class AppUserInfo {

    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "id")
    private Long id;

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
    private Short sex;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 状态 0.正常 1.自己注销 2.管理员注销
     */
    private Short status;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 最新更新人业务主键
     */
    private String lastUpdateUserId;

    /**
     * 设备Id
     */
    private String deviceId;

    /**
     * 用户表业务主键
     */
    private String userId;

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
