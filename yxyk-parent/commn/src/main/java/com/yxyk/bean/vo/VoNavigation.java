package com.yxyk.bean.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Classname RoNavigation
 * @Description TODO
 * @Date 2020/6/16 14:25
 * @Created by cw
 */
@Getter
@Setter
public class VoNavigation {

    /**
     * id
     */
    private Long id;

    /**
     *栏目名称
     */
    @NotBlank(message = "栏目名称不能为空")
    private String navigationName;

    /**
     * 栏目排序
     */
    @NotNull(message = "排序号不能为空")
    private Integer sort;

    /**
     * 父栏目id
     */
    @NotNull(message = "父栏目id能不能为空")
    private Long pId;

    /**
     * 权限码
     */
    @NotNull(message = "权限码不能为空")
    private Long permissionId;
}
