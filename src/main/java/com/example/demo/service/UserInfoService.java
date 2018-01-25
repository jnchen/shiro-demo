package com.example.demo.service;

import com.example.demo.entity.UserInfo;

/**
 * Created by caojingchen on 2018/1/23.
 */
public interface UserInfoService {
    public UserInfo findByUsername(String username);
}
