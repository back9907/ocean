/**
 * @(#)MessaeService.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.service.spi;

import com.ocean.sever.entity.ChatMessage;

import java.util.List;
import java.util.Set;

/**
 * @author back
 */
public interface MessageService {
    long createMessage(long senderId, long receiverId, String mssage);
    Set<ChatMessage> receiveMessage(long userId, long friendId, long lastTime);
    List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime);
}