package com.wudd.handler;

import com.wudd.exception.PasswordException;
import com.wudd.exception.UsernameException;
import dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 用于处理控制层抛出的异常
public class GlobalExceptionHandler {
    @ExceptionHandler // 方法中有异常的具体类，所以注解中不用在去指定类class,@ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage());
        return Result.error("服务器出错误了，请联系管理员",null);
    }

    @ExceptionHandler
    public Result handleUsernameException(UsernameException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage(),null);
    }

    @ExceptionHandler
    public Result handlePasswordException(PasswordException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage(),null);
    }
}