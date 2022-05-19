/**
 * @(#)UseDao.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.spi;

import com.ocean.sever.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author back
 */
public interface UserDao {

    long add(User user);
    Optional<User> find(String email);

    Optional<User> find(long id);

    List<User> findByName(String userName);

    List<User> find(List<Long> user_ids);
}