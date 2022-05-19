/**
 * @(#)ChatController.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.ctrl;

import com.ocean.sever.web.logic.spi.ChatLogic;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author back
 */
@Slf4j
@RestController
@RequestMapping(value = "/ocean/api/chat")
public class ChatController {

    @Autowired
    ChatLogic chatLogic;

    @ApiOperation("用户登录时调用")
    @PostMapping(value = "/log")
    public void start(@RequestParam("userId") int userId) {
        chatLogic.start(userId);
    }
}