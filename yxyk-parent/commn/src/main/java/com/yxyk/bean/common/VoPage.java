package com.yxyk.bean.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/9/28
 * Time：10:20
 */
@Setter
@Getter
public class VoPage {

    /**
     * 分页参数
     */
    @NotNull(message = "分页参数不能为空")
    private Integer pageNum;

    /**
     * 分页参数
     */
    @NotNull(message = "分页参数不能为空")
    private Integer pageSize;
}
