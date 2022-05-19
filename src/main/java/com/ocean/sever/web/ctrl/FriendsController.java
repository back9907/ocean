package com.ocean.sever.web.ctrl;

import com.ocean.sever.config.CommonResult;
import com.ocean.sever.entity.Friend;
import com.ocean.sever.entity.Request;
import com.ocean.sever.web.logic.spi.FriendsLogic;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author back
 */
@Slf4j
@RestController
@RequestMapping(value = "/ocean/api/friend")
public class FriendsController {

    @Autowired
    FriendsLogic friendsLogic;


    @Autowired
    public HttpServletRequest httpServletRequest;

    @ApiOperation("根据userId查询朋友")
    @GetMapping(value = "/id")
    public CommonResult getUserByUserId(@RequestParam("user_id")long userId) {
        List<Friend> friends = friendsLogic.getFriend(userId);
        return friends.isEmpty()
                ?CommonResult.success()
                :CommonResult.successWithData(friends);
    }

    @ApiOperation("根据userId发送好友申请")
    @PostMapping(value = "/request")
    public CommonResult addFriendRequest(@RequestParam("source_id")long sourceId,
                                         @RequestParam("destination_id")long destinationId) {
        Long requestId = friendsLogic.addFriendRequest(sourceId,destinationId);
        return CommonResult.successWithData(requestId);
    }

    @ApiOperation("根据userId查询好友申请")
    @GetMapping(value = "/request")
    public CommonResult getFriendRequest(@RequestParam("destination_id")long destinationId) {
        List<Request> requestId = friendsLogic.getFriendRequest(destinationId);
        return CommonResult.successWithData(requestId);
    }

    @ApiOperation("处理好友申请")
    @DeleteMapping(value = "/handle")
    public CommonResult handleFriendRequest(@RequestBody Request request, @RequestParam("result") boolean result){
        friendsLogic.handleFriendRequest(request,result);
        return CommonResult.success();
    }

    @ApiOperation("屏蔽好友")
    @PatchMapping(value = "/block")
    public CommonResult changeBlock(@RequestParam("id") long id, @RequestParam("isBlock") boolean block){
        friendsLogic.changeBlock(id,block);
        return CommonResult.success();
    }

}