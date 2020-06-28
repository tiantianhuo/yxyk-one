package com.yxyk.bean.po;

import com.yxyk.bean.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Classname Banner
 * @Description TODO
 * @Date 2020/6/16 11:24
 * @Created by cw
 */
@Setter
@Getter
@Table(name = "banner")
@Entity
public class Banner extends IdEntity {

    /**
     * 排序数字
     */
    private Long orderNumber;

    /**
     * 轮播图名称
     */
    private String name;

    /**
     * 轮播图地址
     */
    private String path;

    /**
     * 轮播图状态
     * 1:开启 0:关闭
     */
    private Integer openState;

    /**
     * 栏目id
     */
    private Long navigationId;

}
