package com.yxyk.user.bean.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：15:30
 */
@Setter
@Getter
public class VoUser {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名称
     */
    @NotNull(message = "用户名称不能为空")
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户角色id
     */
    @NotNull(message = "用户角色id不能为空")
    private Long roleId;

    /**
     * 备注
     */
    private String remarks;


}
