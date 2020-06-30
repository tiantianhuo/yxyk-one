package com.yxyk.bean.po;


import com.yxyk.bean.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "admin_article")
public class AdminArticle extends IdEntity {
    private Long sort;
    private Long channel;
    private String title;

    @Lob //longtext
    @Column(columnDefinition="longtext")
    private String content;
    private String startTime;
    private String endTime;
    private String createPerson;
    private String updatePersn;
    private String channelName;
}
