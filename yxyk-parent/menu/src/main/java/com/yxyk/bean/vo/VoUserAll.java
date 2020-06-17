package com.yxyk.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
public class VoUserAll {

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    /**
     * 开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

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
