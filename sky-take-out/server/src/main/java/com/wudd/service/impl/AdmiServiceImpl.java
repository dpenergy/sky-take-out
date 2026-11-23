package com.wudd.service.impl;

import com.wudd.exception.PasswordException;
import com.wudd.exception.UsernameException;
import com.wudd.mapper.AdmiMapper;
import com.wudd.service.AdmiService;
import com.wudd.util.JwtUtils;
import dto.AdmiSearchInfo;
import dto.AdmiSearchResult;
import dto.LoginInfo;
import dto.Result;
import entity.Admi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

        if(admi.getStatus() == 0){
            return Result.error("当前账户已被禁用，请联系管理员开启",null);
        }

        // 比较密码
        if(admi.getPassword().equals(loginInfo.getPassword())){
            String token = JwtUtils.generateToken(admi.getId(),admi.getName());
            loginInfo.setToken(token);
            loginInfo.setName(admi.getName());
            return Result.success("登录成功",loginInfo);
        } else {
            throw new PasswordException("密码错误");
        }
    }

    //@Override
    //public Result queryAdmiList(String searchName) {
    //    List<Admi> employeeList = admiMapper.queryAdmiList(searchName);
    //    if(employeeList.isEmpty()){
    //        return Result.error("暂无数据",null);
    //    }
    //    System.out.println(employeeList.get(0).getUpdateTime().toString());
    //
    //    return Result.success(employeeList);
    //}

    @Override
    public Result queryAdmiList(AdmiSearchInfo admiSearchInfo) {
        int start = admiSearchInfo.getPageSize() * (admiSearchInfo.getCurrentPage() -1);
        admiSearchInfo.setStart(start);

        List<Admi> admiList = admiMapper.queryAdmiList(admiSearchInfo);
        Integer total = admiMapper.admiTotalCounts();

        return Result.success(new AdmiSearchResult(admiList,total));
    }

    @Override
    public Result addAdmi(Admi admi) { // name,username,phoneNumber,birthday,剩下的字段都能自己生成
        // 检查用户名有没有重复
        if(admiMapper.queryByUsername(admi.getUsername()) != null) {
            return Result.error(admi.getUsername()+" 该用户名已经存在！","");
        }

        admiMapper.addAdmi(admi);

        return Result.success("成功添加新员工！",null);
    }

    @Override
    public Result deleteAdmi(Integer id) {
        if(null == admiMapper.queryById(id)) {
            return Result.error("不存在该管理员",null);
        }

        admiMapper.deleteById(id);

        return Result.success("已删除该员工",null);
    }

    @Override
    public Result editAdmi(Admi admi) {
        admiMapper.editAdmi(admi);

        return Result.success("已保存修改",null);
    }
}
