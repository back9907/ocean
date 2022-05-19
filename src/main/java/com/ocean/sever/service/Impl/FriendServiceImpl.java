package com.ocean.sever.service.Impl;

import com.ocean.sever.entity.Friend;
import com.ocean.sever.entity.Request;
import com.ocean.sever.entity.User;
import com.ocean.sever.service.spi.FriendService;
import com.ocean.sever.service.spi.UserService;
import com.ocean.sever.storage.spi.FriendDao;
import com.ocean.sever.storage.spi.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author back
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendDao friendDao;

    @Autowired
    RequestDao requestDao;

    @Autowired
    UserService userService;

    @Override
    public long addFriendRequest(long sourceId, long destinationId) {
        return requestDao.addFriendRequest(sourceId,destinationId);
    }

    @Override
    public List<Request> getFriendRequest(long destinationId) {
        return requestDao.getFriendRequest(destinationId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean approveFriendRequest(Request request) {
        requestDao.deleteFriendRequest(request.getId());
        Optional<User> user1 = userService.findUserByUserId(request.getDestination_id());
        friendDao.addFriend(request.getSource_id(),user1.get());
        Optional<User> user2 = userService.findUserByUserId(request.getSource_id());
        friendDao.addFriend(request.getDestination_id(), user2.get());
        return true;
    }

    @Override
    public boolean rejectFriendRequest(Request request) {
        requestDao.deleteFriendRequest(request.getId());
        return true;
    }

    @Override
    public List<Friend> getFriend(long userId) {
        return friendDao.getFriendById(userId);
    }

    @Override
    public boolean changeBlock(long id, boolean block) {
        return friendDao.changeBlock(id,block);
    }
}