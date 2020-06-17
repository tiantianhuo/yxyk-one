package com.yxyk.bean.po;

import com.yxyk.bean.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "")
public class Article extends IdEntity {
    private int sort;
    private String channel;
    private String title;
    private String content;
    private int satate;
}
