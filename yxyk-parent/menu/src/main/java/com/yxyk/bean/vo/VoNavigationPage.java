package com.yxyk.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yxyk.bean.common.VoPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * 推送信息查询所有条件封装
 * @author fhx
 * @date 2019/8/5 16:44
 */
@Data
@ApiModel(description = "推送信息查询条件")
public class VoNavigationPage extends VoPage {

     /**
     *  栏目 pid
      */
    private Long pid=0L;

}
