package com.wudd.service;

import dto.AdmiSearchInfo;
import dto.LoginInfo;
import dto.Result;
import entity.Admi;

public interface AdmiService {
    Result login(LoginInfo loginInfo);

    //Result queryAdmiList(String searchName);

    Result queryAdmiList(AdmiSearchInfo admiSearchInfo);

    Result addAdmi(Admi admi);

    Result deleteAdmi(Integer id);

    Result editAdmi(Admi admi);
}
