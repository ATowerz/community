package com.atz.springboot.service;

import com.atz.springboot.mapper.UserMapper;
import com.atz.springboot.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
//
//        List<User> users = userMapper.findByID(user.getId());
//        if (users.size() == 0) {
//            // 插入
//            user.setGmtCreate(System.currentTimeMillis());
//            user.setGmtModified(user.getGmtCreate());
//            userMapper.insert(user);
//        } else {
//            //更新
//            User dbUser = users.get(0);
//            User updateUser = new User();
//            updateUser.setGmtModified(System.currentTimeMillis());
//            updateUser.setAvatarUrl(user.getAvatarUrl());
//            updateUser.setName(user.getName());
//            updateUser.setToken(user.getToken());
//            UserExample example = new UserExample();
//            example.createCriteria()
//                    .andIdEqualTo(dbUser.getId());
//            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
