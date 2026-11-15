package com.wudd.exception;
// 异常是纯java类，所以放到通用common模块

public class UsernameException extends RuntimeException{
    public UsernameException(String message){
        super(message);
    }

    public UsernameException() {
    }
}
