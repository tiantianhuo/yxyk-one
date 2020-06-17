package com.yxyk.bean.po;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yxyk.bean.common.IdEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "")
public class Sensitive extends IdEntity {

    private String sensitiveword; //敏感词
    private String replaceto;   //替换词
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
    private String createPerson; //创建人
    private int states;  //状态0正常，1删除

}