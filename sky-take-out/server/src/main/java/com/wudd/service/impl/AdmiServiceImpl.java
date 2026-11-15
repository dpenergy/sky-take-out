package com.wudd.service.impl;

import com.wudd.exception.PasswordException;
import com.wudd.exception.UsernameException;
import com.wudd.mapper.AdmiMapper;
import com.wudd.service.AdmiService;
import com.wudd.util.JwtUtils;
import dto.LoginInfo;
import dto.Result;
import entity.Admi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmiServiceImpl implements AdmiService {
    @Autowired
    private AdmiMapper admiMapper;

    @Override
    public Result login(LoginInfo loginInfo) {
        // 通过用户名查询管理员账户
        Admi admi = admiMapper.queryByUsername(loginInfo.getUsername());
        if(admi == null){
            // 这个地方应该抛出异常，然后再让全局异常处理器来处理
            //return Result.error("该用户不存在",null);
            throw new UsernameException("该用户不存在");
        }

        // 比较密码
        if(admi.getPassword().equals(loginInfo.getPassword())){
            String token = JwtUtils.generateToken(admi.getId(),admi.getName());
            return Result.success("登录成功",token);
        } else {
            throw new PasswordException("密码错误");
        }
    }
}
