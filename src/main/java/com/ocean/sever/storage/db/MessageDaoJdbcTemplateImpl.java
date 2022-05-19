/**
 * @(#)MessageDaoJdbcTemplateImpl.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.db;

import com.ocean.sever.entity.ChatMessage;
import com.ocean.sever.storage.spi.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author back
 */
@Repository
public class MessageDaoJdbcTemplateImpl implements MessageDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<ChatMessage> ROWMAPPER= (ResultSet rs, int rowNum)->{
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(rs.getString("content"));
        chatMessage.setId(rs.getLong("message_id"));
        chatMessage.setReceiverId(rs.getLong("receiver_id"));
        chatMessage.setSenderID(rs.getLong("sender_id"));
        chatMessage.setTime(rs.getTimestamp("create_time").getTime());
        return chatMessage;
    };

    @Override
    public long createMessage(ChatMessage chatMessage) {
        String sql = "insert into message_info (sender_id,receiver_id,content) values (:sender_id,:receiver_id,:content)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("sender_id",chatMessage.getSenderID());
        mapSqlParameterSource.addValue("receiver_id",chatMessage.getReceiverId());
        mapSqlParameterSource.addValue("content",chatMessage.getMessage());
        KeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource,key);
        return key.getKey().longValue();
    }

    @Override
    public List<ChatMessage> receiveMessage(long userId, long friendId, long lastTime) {
        String sql = "SELECT * from message_info WHERE ((receiver_id = :receiver_id AND sender_id = :sender_id) OR" +
                " (receiver_id = :sender_id AND sender_id = :receiver_id))" +
                " AND  UNIX_TIMESTAMP(create_time) * 1000 > :create_time;";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("sender_id",friendId);
        mapSqlParameterSource.addValue("receiver_id", userId);
        mapSqlParameterSource.addValue("create_time", lastTime);
        return namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,ROWMAPPER);
    }

    @Override
    public List<ChatMessage> receiveAllFriendNewMessage(long userId, long lastTime) {
//        String sql = "select * from (select *, ROW_NUMBER() " +
//                "over(partition by sender_id order by create_time desc) as time_order from message_info) as t " +
//                "where time_order = 1 AND receiver_id = :receiver_id AND create_time> :create_time";
        String sql = "select distinct b.message_id, b.sender_id, b.receiver_id, b.content, a.create_time "
        +"from (select sender_id, receiver_id, max(create_time) as create_time from message_info where receiver_id = :receiver_id group by sender_id, receiver_id) a join"
        +"(select message_id,sender_id, receiver_id, content, create_time from message_info where receiver_id = :receiver_id) " +
                "b on a.sender_id = b.sender_id and a.create_time = b.create_time";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("receiver_id", userId);
        mapSqlParameterSource.addValue("create_time", lastTime);
        return namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,ROWMAPPER);
    }
}