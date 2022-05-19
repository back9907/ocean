package com.ocean.sever.web.logic.Impl;

import com.ocean.sever.config.Client;
import com.ocean.sever.entity.ChatMessage;
import com.ocean.sever.service.spi.MessageService;
import com.ocean.sever.web.logic.spi.ChatLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author back
 */
@Service
public class ChatLogicImpl implements ChatLogic {

    @Autowired
    Client client;

    @Autowired
    MessageService messageService;


    public void sendMessage(long sourceId, long destinationId, String content) {
        String message = sourceId+"#"+destinationId+"#"+content;
        client.sendMessage(message);
    }

    @Override
    public void sendMessage(long userId, String content,long destination) {
        client.sendMessage(content);
    }

    @Override
    public List<ChatMessage> receiveMessage(long userId, long friendId, long lastTime) {
        return messageService.receiveMessage(userId, friendId, lastTime);
    }

    @Override
    public List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime) {
        return messageService.receiveAllFriendNewMessage(userId,lastTime);
    }

    public void start(long userId){
        client.startUp(userId);
    }
}