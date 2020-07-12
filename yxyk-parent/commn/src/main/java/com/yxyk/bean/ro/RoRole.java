package com.yxyk.bean.ro;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author: cw
 * @Description:
 * @Date: create in 2020/7/7 21:18
 */
@Getter
@Setter
public class RoRole {

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
