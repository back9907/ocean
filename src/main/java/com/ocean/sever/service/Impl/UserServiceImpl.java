/**
 * @(#)UserServiceImpl.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.service.Impl;

import com.ocean.sever.entity.User;
import com.ocean.sever.service.spi.UserService;
import com.ocean.sever.storage.spi.UseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author back
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UseDao useDao;

    @Override
    public Optional<User> findUserByUserEmail(String email) {
        return useDao.find(email);
    }
}