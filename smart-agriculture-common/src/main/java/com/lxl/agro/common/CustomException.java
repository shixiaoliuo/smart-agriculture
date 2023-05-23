package com.lxl.agro.common;

/**
 * 自定义异常：当我们的代码出现了业务异常的时候，都抛出此异常
 */
public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}
