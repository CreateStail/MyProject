package com.synway.exception;

import com.synway.utils.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常处理控制器
 */
@ControllerAdvice
public class YthExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public JsonData handler(Exception e){
        if( e instanceof  YthException){
            YthException ythException = (YthException)e;
            return JsonData.buildError(ythException.getMsg(),ythException.getCode());
        }else{
            return JsonData.buildError("全局异常,未知错误");
        }
    }
}
