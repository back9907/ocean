package com.ocean.sever.web.logic.Impl;

import com.ocean.sever.config.CommonResult;
import com.ocean.sever.entity.User;
import com.ocean.sever.service.spi.UserService;
import com.ocean.sever.web.logic.spi.UserLogic;
import com.ocean.sever.web.vo.UserVO;
import com.ocean.sever.web.wrapper.UserVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author back
 */
@Service
public class UserLogicImpl implements UserLogic {
    @Autowired
    UserService userService;

    @Override
    public Optional<UserVO> findUserByUserId(long userId){
        if (!userService.findUserByUserId(userId).isPresent()){
            return Optional.empty();
        }
        return Optional.of(UserVOWrapper.wrapper(userService.findUserByUserId(userId).get()));
    }

    @Override
    public Optional<User> findUserByUserEmail(String email) {
        return userService.findUserByUserEmail(email);
    }

    @Override
    public Optional<UserVO> findUserByUserVOEmail(String email) {
        return Optional.of(UserVOWrapper.wrapper(userService.findUserByUserEmail(email).get()));
    }

    @Override
    public List<User> findByName(String userName) {
        return userService.findByName(userName);
    }

    @Override
    public CommonResult logIn(String email, String password) {
        Optional<User> user = userService.findUserByUserEmail(email);
        if(!user.isPresent()){
            return CommonResult.userNotExist();
        }
        return password.equals(user.get().getPassword())?CommonResult.successWithData(findUserByUserVOEmail(email))
                                                        :CommonResult.result(401,"Account password does not match",null);
    }

    @Override
    public long registerUser(String user_name, String name, String password, Date birthday, String email) {
        User user = User.builder().userName(user_name)
                .date(birthday)
                .name(name)
                .email(email)
                .userId(0)
                .password(password).build();
        return userService.registerUser(user);
    }


}