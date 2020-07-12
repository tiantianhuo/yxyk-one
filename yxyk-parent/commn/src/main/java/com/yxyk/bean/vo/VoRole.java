package com.yxyk.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: cw
 * @Description:
 * @Date: create in 2020/7/7 21:18
 */
@Getter
@Setter
public class VoRole {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 备注
     */
    private String remarks;
    
}
