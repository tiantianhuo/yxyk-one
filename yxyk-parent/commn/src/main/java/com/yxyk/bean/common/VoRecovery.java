package com.yxyk.bean.common;

import lombok.Getter;
import lombok.Setter;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/11/5
 * Time：10:19
 */
@Setter
@Getter
public class VoRecovery extends VoPage {

    /**
     * 类别id
     */
    private Long modularId;

    /**
     * 排序字段
     */
    private String sortFiled;

    /**
     * 线索描述
     */
    private String reportDesc;

}
