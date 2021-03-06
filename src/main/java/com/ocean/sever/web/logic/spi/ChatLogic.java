package com.ocean.sever.web.logic.spi;

import com.ocean.sever.entity.ChatMessage;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author back
 */
public interface ChatLogic {
    void sendMessage(long userId, String content,long destination) throws IOException;
    Set<ChatMessage> receiveMessage(long userId, long friendId, long lastTime);
    List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime);

    void start(long userId);
}