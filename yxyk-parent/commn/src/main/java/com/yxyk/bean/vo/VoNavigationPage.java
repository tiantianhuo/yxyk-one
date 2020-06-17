package com.yxyk.bean.vo;

import com.yxyk.bean.common.VoPage;
import io.swagger.annotations.ApiModel;
import lombok.Data;

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
