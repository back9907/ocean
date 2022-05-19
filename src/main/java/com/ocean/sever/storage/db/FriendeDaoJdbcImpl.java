package com.ocean.sever.storage.db;

import com.ocean.sever.entity.Friend;
import com.ocean.sever.entity.User;
import com.ocean.sever.storage.spi.FriendDao;
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
public class FriendeDaoJdbcImpl implements FriendDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<Friend> ROWMAPPER= (ResultSet rs, int rowNum)->{
        Friend friend = new Friend();
        friend.setUserId(rs.getInt("friend_id"));
        friend.setUserName(rs.getString("friend_name"));
        friend.setBlock(rs.getBoolean("is_blocked"));
        return friend;
    };


    @Override
    public List<Friend> getFriendById(long userId) {
        String sql = "select * FROM user_relation WHERE user_id = :user_id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user_id", userId);
        return namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,ROWMAPPER);
    }

    @Override
    public long addFriend(long userId, User friend) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into user_relation (user_id, friend_id, friend_name) values (:user_id,:friend_id, :friend_name)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user_id",userId);
        mapSqlParameterSource.addValue("friend_id",friend.getUserId());
        mapSqlParameterSource.addValue("friend_name", friend.getUserName());
        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public boolean changeBlock(long id, boolean block) {
        String sql = "update user_relation set is_blocked = :is_blocked where id = :id ";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("is_blocked",block);
        mapSqlParameterSource.addValue("id",id);
        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
        return true;
    }


}