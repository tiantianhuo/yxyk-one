package com.yxyk.bean.po;

import com.yxyk.bean.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "permission")
@Entity
public class Permission extends IdEntity {

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限码
     */
    private String permissionCode;


}
