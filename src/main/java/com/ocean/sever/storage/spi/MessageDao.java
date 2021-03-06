/**
 * @(#)MessageDao.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.spi;

import com.ocean.sever.entity.ChatMessage;

import java.util.List;
import java.util.Set;

/**
 * @author back
 */
public interface MessageDao {
    long createMessage(ChatMessage chatMessage);
    Set<ChatMessage> receiveMessage(long userId, long friendId, long lastTime);
    List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime);
}