package com.yxyk.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/7
 * Time：15:08
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "customer.upload")
public class UploadProperties {

    private String serviceUrl;

    private String gainUrl;

    private String requestUrl;

    private String downUrl;

    private String textUrl;
}
