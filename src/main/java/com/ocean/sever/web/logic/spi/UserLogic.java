package com.ocean.sever.web.logic.spi;

import com.ocean.sever.config.CommonResult;
import com.ocean.sever.entity.User;
import com.ocean.sever.web.vo.UserVO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author back
 */
public interface UserLogic {
    Optional<UserVO> findUserByUserId(long userId);
    Optional<User> findUserByUserEmail(String email);
    public Optional<UserVO> findUserByUserVOEmail(String email);
    List<User> findByName(String userName);
    CommonResult logIn(String email, String password);
    long registerUser( String user_name, String password,String name,Date birthday, String email);
}