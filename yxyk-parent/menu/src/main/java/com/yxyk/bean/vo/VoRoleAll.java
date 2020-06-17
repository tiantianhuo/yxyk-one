package com.yxyk.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ay
 * Date: 2019/10/11
 * Time: 17:03
 */
@Getter
@Setter
public class VoRoleAll {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

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
