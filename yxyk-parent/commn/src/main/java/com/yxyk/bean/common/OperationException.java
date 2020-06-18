package com.yxyk.bean.common;

import lombok.NoArgsConstructor;

/**
 *  自定义异常信息
 *
 * Created with IntelliJ IDEA.
 * author: create by ZhangHao
 * Date: 2019/7/10
 * Time: 15:46
 */
@NoArgsConstructor
public class OperationException extends Exception {
    private static final long serialVersionUID = 1L;

    public OperationException(String message) {
        super(message);
    }
}
