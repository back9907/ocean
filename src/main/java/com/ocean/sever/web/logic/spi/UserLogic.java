/**
 * @(#)UserLogic.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.logic.spi;

import com.ocean.sever.entity.User;

import java.util.Optional;

/**
 * @author back
 */
public interface UserLogic {
    Optional<User> findUserByUserVOEmail(String email);
}