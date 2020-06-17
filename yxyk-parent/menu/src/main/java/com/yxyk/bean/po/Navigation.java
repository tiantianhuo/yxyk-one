package com.yxyk.bean.po;

import com.yxyk.bean.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Table(name = "navigation")
@Entity
public class Navigation extends IdEntity {

    /**
     *栏目名称
     */
    private String navigationName;

    /**
     * 栏目排序
     */
    private Integer sort;

    /**
     * 父栏目id
     */
    private Long pId;

    /**
     * 权限码
     */
    private String permissionCode;
}
