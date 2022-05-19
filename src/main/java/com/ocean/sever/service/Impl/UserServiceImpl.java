/**
 * @(#)UserServiceImpl.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.service.Impl;

import com.ocean.sever.entity.User;
import com.ocean.sever.service.spi.UserService;
import com.ocean.sever.storage.spi.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author back
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Optional<User> findUserByUserId(long userId){
        return userDao.find(userId);
    }

    @Override
    public Optional<User> findUserByUserEmail(String email){
        return userDao.find(email);
    }

    @Override
    public long registerUser(User user) {
        return userDao.add(user);
    }

    @Override
    public List<User> find(List<Long> user_ids) {
        if (user_ids.isEmpty()){
            return new ArrayList<>();
        }
        return userDao.find(user_ids);
    }

    @Override
    public List<User> findByName(String userName) {
        return userDao.findByName(userName);
    }
}