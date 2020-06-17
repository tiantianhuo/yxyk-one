package com.yxyk.bean.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class VoId {

    /**
     * 检察院id
     */
    private Long id;

    /**
     * 详情ID
     */
    private Long detailsId;

    /**
     * 分页页码
     */
    @NotNull(message = "请输入起始页")
    private Integer pageNum;

    /**
     * 每页最大条数
     */
    @NotNull(message = "请输入每页最大条数")
    private Integer pageSize;

}
