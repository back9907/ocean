package com.ocean.sever.web.logic.spi;


import com.ocean.sever.entity.Friend;
import com.ocean.sever.entity.Request;

import java.util.List;

/**
 * @author back
 */
public interface FriendsLogic {

    long addFriendRequest(long sourceId, long destinationId);
    boolean handleFriendRequest(Request request, boolean result);
    List<Friend> getFriend(long userId);
    List<Request>getFriendRequest(long destinationId);
    boolean changeBlock(long id, boolean block);
}