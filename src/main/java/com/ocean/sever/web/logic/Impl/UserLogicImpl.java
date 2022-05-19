/**
 * @(#)UserLogicIml.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.logic.Impl;

import com.ocean.sever.entity.User;
import com.ocean.sever.service.spi.UserService;
import com.ocean.sever.web.logic.spi.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author back
 */
@Service
public class UserLogicImpl implements UserLogic {

    @Autowired
    UserService userService;

    @Override
    public Optional<User> findUserByUserVOEmail(String email) {
        return userService.findUserByUserEmail(email);
    }
}