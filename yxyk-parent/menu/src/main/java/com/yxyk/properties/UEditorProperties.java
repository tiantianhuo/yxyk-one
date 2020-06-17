package com.yxyk.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/22
 * Time：15:18
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "customer.ueditor")
public class UEditorProperties {

    private String resourceUrl;

}
