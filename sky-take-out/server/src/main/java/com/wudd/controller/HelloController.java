package com.wudd.controller;

import dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@RestController
@Tag(name="简单试试相关")
public class HelloController {
    // 大脑现在有些模糊了，我们来梳理一下现在的进度
    // 创建后端工程，父子模块继承，依赖添加-配置添加
    // springdoc-openapi和knife4j引入

    // Maven模块管理
    // 日志技术配置
    // 接口文档生成技术引入和简单使用

    // 接下来还需要做一些全局准备工作
    // 全局异常处理器
    // 登录校验请求拦截令牌之类的
    // 定义全局常量，全局异常之类的

    @Operation(summary = "hello请求")
    @GetMapping("/hello")
    public Result hello(){
        log.info("hello");
        return Result.success("hello");
    }

    @DeleteMapping("/delete")
    public Result delete(String name,String id){
        return Result.success("Already delete!");
    }
}
