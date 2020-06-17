package com.yxyk.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhanghao
 * date: 2019/10/10 16:43
 * description:
 */
@Data
public class VoBannerSys {

    /**
     * 查询字段
     */
    private String name;


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

    private Long pid=0L;
}
