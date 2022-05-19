/**
 * @(#)ChatLogicImpl.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.logic.Impl;

import com.ocean.sever.config.Client;
import com.ocean.sever.entity.ChatMessage;
import com.ocean.sever.web.logic.spi.ChatLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author back
 */
@Service
public class ChatLogicImpl implements ChatLogic {

    @Autowired
    Client client;

    @Override
    public void sendMessage(long userId, String content, long destination) throws IOException {
        client.startUp(userId);
    }

    @Override
    public List<ChatMessage> receiveMessage(long userId, long friendId, long lastTime) {
        return null;
    }

    @Override
    public List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime) {
        return null;
    }

    @Override
    public void start(long userId) {
        client.startUp(userId);
    }
}