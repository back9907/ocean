/**
 * @(#)UserService.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.service.spi;

import com.ocean.sever.entity.User;

import java.util.Optional;

/**
 * @author back
 */
public interface UserService {
    Optional<User> findUserByUserEmail(String email);
}