package com.wudd.service;

import dto.LoginInfo;
import dto.Result;

public interface AdmiService {
    Result login(LoginInfo loginInfo);
}
