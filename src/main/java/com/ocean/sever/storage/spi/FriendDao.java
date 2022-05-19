package com.ocean.sever.storage.spi;


import com.ocean.sever.entity.Friend;
import com.ocean.sever.entity.User;

import java.util.List;

/**
 * @author back
 */
public interface FriendDao {

    List<Friend> getFriendById(long userId);
    long addFriend(long userId, User friend);
    boolean changeBlock(long id, boolean block);
}