/**
 * @(#)UserVOWrapper.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.wrapper;

import com.ocean.sever.entity.User;
import com.ocean.sever.web.vo.UserVO;

/**
 * @author back
 */
public class UserVOWrapper {

    public static UserVO wrapper (User user){
        if (user == null){
            return null;
        }

        UserVO vo = UserVO.builder().userId(user.getUserId())
                .email(user.getEmail())
                .birthday(user.getDate())
                .userName(user.getUserName())
                .name(user.getName()).build();
        return vo;
    }
}