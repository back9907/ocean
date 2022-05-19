package com.ocean.sever.web.ctrl;

import com.ocean.sever.config.CommonResult;
import com.ocean.sever.web.logic.spi.ChatLogic;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author back
 */
@Slf4j
@RestController
@RequestMapping(value = "/ocean/api/chat")
public class ChatController {

    @Autowired
    ChatLogic chatLogic;

    @ApiOperation("根据userId给用户发消息")
    @PostMapping(value = "/id")
    public CommonResult sendMessage(@RequestParam("user_id")long sourceId, @RequestParam("destination_id")long destinationId, @RequestParam("content")String content) throws IOException {
        chatLogic.sendMessage(sourceId,content,destinationId);
        return CommonResult.success();
    }

    @ApiOperation("用户登录时调用")
    @PostMapping(value = "/log")
    public void start(@RequestParam("userId") int userId) {
        chatLogic.start(userId);
    }

    @ApiOperation("获取上次刷新后的新消息")
    @GetMapping(value = "/message")
    public CommonResult receiveMessage(@RequestParam("user_id")long userId, @RequestParam("friend_id")long friendId, @RequestParam("last request")long lastTime){
        return CommonResult.successWithData(chatLogic.receiveMessage(userId, friendId,lastTime));
    }

    @ApiOperation("获取所有好友的新消息")
    @GetMapping(value = "/friendMessage")
    public CommonResult receiveAllFriendMessage(@RequestParam("user_id")long userId, @RequestParam("last request")long lastTime){
        return CommonResult.successWithData(chatLogic.receiveAllFriendNewMessage(userId, lastTime));
    }


}