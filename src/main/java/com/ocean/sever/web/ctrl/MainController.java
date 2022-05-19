/**
 * @(#)MainController.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.ctrl;

import com.ocean.sever.config.CommonResult;
import com.ocean.sever.web.logic.spi.UserLogic;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author back
 */
@Slf4j
@RestController
@RequestMapping(value = "/ocean/api/user")
public class MainController {

    @Autowired
    public UserLogic userLogic;

    @Autowired
    public HttpServletRequest httpServletRequest;

    @ApiOperation("根据email查询用户")
    @GetMapping(value = "/email")
    public CommonResult getUserByUserId(@RequestParam("email")String email) {
        return userLogic.findUserByUserVOEmail(email).isPresent()
                ?CommonResult.successWithData(userLogic.findUserByUserVOEmail(email))
                : CommonResult.userNotExist();
    }

    @ApiOperation("验证账号密码")
    @GetMapping(value = "/login")
    public CommonResult logIn(@RequestParam("email")String email, @RequestParam("password")String password){
        return userLogic.logIn(email,password);
    }
}