package com.example.demo.repository;

import com.example.demo.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by caojingchen on 2018/1/23.
 */
public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {
    public UserInfo findByUsername(String username);
}
