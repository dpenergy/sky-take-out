package com.wudd.controller;

import com.wudd.service.AdmiService;
import dto.AdmiSearchInfo;
import dto.LoginInfo;
import dto.Result;
import entity.Admi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/admi")
@Tag(name = "管理员相关")
public class AdmiController {
    @Autowired
    AdmiService admiService;

    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo loginInfo) {
        log.info("登录请求"); // 在拦截器里面去写具体的日志或者用springAOP去写这些比较统一的日志
        return admiService.login(loginInfo);
    }

    //@Operation(summary = "获取管理员列表")
    //@GetMapping("/list")
    //public Result queryAdmiList(String searchName) {
    //    log.info("查询管理员列表请求");
    //    return admiService.queryAdmiList(searchName);
    //}

    // 先不破坏原来的使用链条，并行着写一个新的链条，测试通过了才替换掉原来的
    @Operation(summary = "获取管理员列表（分页查询）")
    @GetMapping("/list")
    public Result queryAdmiList(AdmiSearchInfo admiSearchInfo) {
        log.info("分页查询管理员列表请求");
        return admiService.queryAdmiList(admiSearchInfo);
    }


    @PostMapping
    @Operation(summary = "添加管理员")
    public Result addAdmi(@RequestBody Admi admi) { // name,username,phoneNumber,birthday
        log.info("添加管理员请求");
        return admiService.addAdmi(admi);
    }

    @DeleteMapping
    @Operation(summary = "删除管理员")
    public Result deleteAdmi(Integer id) {
        log.info("删除管理员请求");
        return admiService.deleteAdmi(id);
    }

    @PutMapping
    @Operation(summary = "修改管理员的信息")
    public Result editAdmi(@RequestBody Admi admi) {
        // 后期多表关联，并且有并发操作，所以不能简单的先删后增，老实写动态SQL来实现
        log.info("修改管理员信息请求");
        return admiService.editAdmi(admi);
    }
}
