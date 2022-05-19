package com.ocean.sever.service.spi;

import com.ocean.sever.entity.Friend;
import com.ocean.sever.entity.Request;

import java.util.List;

/**
 * @author back
 */
public interface FriendService {
    long addFriendRequest(long sourceId, long destinationId);
    List<Request> getFriendRequest(long destinationId);
    boolean approveFriendRequest(Request request);
    boolean rejectFriendRequest(Request request);
    List<Friend> getFriend(long userId);
    boolean changeBlock(long id, boolean block);
}