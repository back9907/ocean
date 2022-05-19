/**
 * @(#)ChatLogic.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.logic.spi;

import com.ocean.sever.entity.ChatMessage;

import java.io.IOException;
import java.util.List;

/**
 * @author back
 */
public interface ChatLogic {
    void sendMessage(long userId, String content,long destination) throws IOException;
    List<ChatMessage> receiveMessage(long userId, long friendId, long lastTime);
    List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime);
    void start(long userId);
}