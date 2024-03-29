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
@Table(name = "admin_sensitive")
public class   AdminSensitive extends IdEntity {
    private String sensitiveword; //敏感词
    private String replaceto;   //替换词
    private String createPerson; //创建人
    private String updataPerson; //修改人
   // private int states;  //状态0正常，1删除
    private  Long  sort;
}