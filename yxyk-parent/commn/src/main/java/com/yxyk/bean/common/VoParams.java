package com.yxyk.bean.common;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author fhx
 * @date 2019/8/15 11:20
 */
@Getter
@Setter
@ApiModel(description = "公共id")
public class VoParams {

    @NotNull(message = "id不能为空")
    private Long id;
}
