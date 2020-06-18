package com.yxyk.user.bean.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/9/27
 * Time：14:30
 */
@Setter
@Getter
@MappedSuperclass
public abstract class IdEntity implements Serializable {

    /**
     * id
     * 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "id", columnDefinition = "bigint(10) comment '主键'")
    private Long id;
    /**
     * 创建时间
     * 自动设置
     */
    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 修改时间
     * 自动设置
     */
    @UpdateTimestamp
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;

    /**
     * 删除标识
     * 0: 未删
     * 1: 已删除
     */
    private int deleteState = 0;
}
