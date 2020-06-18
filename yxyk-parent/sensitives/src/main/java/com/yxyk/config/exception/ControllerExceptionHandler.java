package com.yxyk.config.exception;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.controller.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/9/28
 * Time：11:30
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler extends BaseController {

    /**
     * 代码级别异常
     *
     * @param ex 异常
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public JSONResponse exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return this.error("系统错误");
    }

    /**
     * 自定义异常捕获
     *
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(OperationException.class)
    private JSONResponse operationException(OperationException e) {
        error(e.getMessage());
        return this.error(e.getMessage());
    }

    /**
     * 单属性验证未通过异常
     *
     * @param e 异常
     * @return 自定义message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private JSONResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return this.error(message);
    }
}
