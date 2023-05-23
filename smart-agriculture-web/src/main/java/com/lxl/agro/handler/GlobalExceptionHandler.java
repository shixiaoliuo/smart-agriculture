package com.lxl.agro.handler;


import com.lxl.agro.common.CustomException;
import com.lxl.agro.common.ResultInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.handler
 * Description : GlobalExceptionHandler
 * Author : LiuXinLei
 * createDate : 2023/5/20 17:00
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * mysql字段重复，在有唯一约束时抛出的异常
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultInfo handleDuplicateName(DuplicateKeyException e){
        //1.返回给用户
        return ResultInfo.error("名称不能重复");
    }


    /**
     * 处理系统异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo handleSystemException(Exception e){
        //1.输出异常
        e.printStackTrace();
        //2.返回结果不用告诉用户是什么异常
        return ResultInfo.error("服务器开小差了，请稍后重试.....");
    }

    /**
     * 处理业务异常
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResultInfo handleCustomException(Exception e){
        //1.使用者要知道错在哪了，因此要有消息提供给用户
        return ResultInfo.error(e.getMessage());
    }


}
