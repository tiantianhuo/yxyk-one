package com.yxyk.bean.ro;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname RoNavigation
 * @Description TODO
 * @Date 2020/6/16 14:25
 * @Created by cw
 */
@Getter
@Setter
public class RoNavigation {

    /**
     * 栏目名称
     */
    private String navigationName;

    /**
     * 栏目排序
     */
    private Integer sort;

    /**
     * 父栏目id
     */
    private String pId;

    /**
     * 权限码
     */
    private String permissionCode;
}
