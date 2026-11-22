package com.wudd.service;

import dto.AdmiSearchInfo;
import dto.LoginInfo;
import dto.Result;

public interface AdmiService {
    Result login(LoginInfo loginInfo);

    //Result queryAdmiList(String searchName);

    Result queryAdmiList(AdmiSearchInfo admiSearchInfo);
}
