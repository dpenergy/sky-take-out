package com.wudd.controller;

import com.wudd.service.AdmiService;
import dto.LoginInfo;
import dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/login")
@Tag(name = "管理员相关")
public class AdmiController {
    @Autowired
    AdmiService admiService;

    @Operation(summary = "管理员登录")
    @PostMapping
    public Result login(@RequestBody LoginInfo loginInfo) {
        log.info("登录请求"); // 在拦截器里面去写具体的日志或者用springAOP去写这些比较统一的日志
        log.info("loginInfo={}", loginInfo);
        return admiService.login(loginInfo);
    }
}
