package com.xjl.service;

import com.xjl.manager.service.UserService;
import com.xjl.mapper.UserMapper;
import com.xjl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
