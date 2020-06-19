package com.yxyk.bean.vo;

import com.yxyk.bean.common.VoPage;
import lombok.Getter;
import lombok.Setter;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/12
 * Time：20:01
 */
@Setter
@Getter
public class VoUserSearch extends VoPage {

    private Long provinceId;

    private Long cityId;

    private Long countyId;

    private String startTime;

    private String endTime;

    private String userName;

}
