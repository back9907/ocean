/**
 * @(#)MessageServiceImpl.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.service.Impl;

import com.ocean.sever.entity.ChatMessage;
import com.ocean.sever.service.spi.MessageService;
import com.ocean.sever.storage.spi.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author back
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Override
    public long createMessage(long senderId, long receiverId, String mssage) {
        ChatMessage chatMessage = ChatMessage.builder().message(mssage)
                .senderID(senderId)
                .receiverId(receiverId).build();
        return messageDao.createMessage(chatMessage);
    }

    @Override
    public Set<ChatMessage> receiveMessage(long userId, long friendId, long lastTime) {
        return messageDao.receiveMessage(userId,friendId, lastTime);
    }

    @Override
    public List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime) {
        return messageDao.receiveAllFriendNewMessage(userId, lastTime);
    }
}