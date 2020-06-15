package com.yxyk.bean.po;

import com.yxyk.bean.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 角色权限表
 *
 * @author tyl
 * @date 2019-1-3
 */
@Setter
@Getter
@Table(name = "role")
@Entity
public class Role extends IdEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 权限
     */
    private String permissions;

    /**
     * 父级
     */
    private Long pid;

    /**
     * 所属检察院
     */
    private Long procuratorId;

}
