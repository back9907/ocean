package com.ocean.sever.web.logic.Impl;


import com.ocean.sever.entity.Friend;
import com.ocean.sever.entity.Request;
import com.ocean.sever.service.spi.FriendService;
import com.ocean.sever.web.logic.spi.FriendsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author back
 */
@Service
public class FriendsLogicImpl implements FriendsLogic {

    @Autowired
    FriendService friendService;
    @Override
    public long addFriendRequest(long sourceId, long destinationId) {
        return friendService.addFriendRequest(sourceId,destinationId);
    }

    @Override
    public boolean handleFriendRequest(Request request, boolean result) {
        return result?friendService.approveFriendRequest(request):friendService.rejectFriendRequest(request);
    }

    @Override
    public List<Friend> getFriend(long userId) {
        return friendService.getFriend(userId);
    }

    @Override
    public List<Request> getFriendRequest(long destinationId) {
        return friendService.getFriendRequest(destinationId);
    }

    @Override
    public boolean changeBlock(long id, boolean block) {
        return friendService.changeBlock(id,block);
    }
}