package com.yxyk.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname VoBanner
 * @Description TODO
 * @Date 2020/6/18 9:01
 * @Created by cw
 */

@Getter
@Setter
public class VoBanner {

    private Long id;
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
