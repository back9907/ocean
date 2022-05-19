/**
 * @(#)UserDaoImpl.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.db;

import com.ocean.sever.entity.User;
import com.ocean.sever.storage.spi.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * @author back
 */
@Repository
public class UserDaoJdbcTemplateImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public long add(User user) {
        String sql = "insert into user_info (user_name, email, password, birthday, name) values (:user_name, :email, :password, :birthday, :name)";
        KeyHolder key = new GeneratedKeyHolder();
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("user_name", user.getUserName());
        paramMap.addValue("email", user.getEmail());
        paramMap.addValue("password",user.getPassword());
        paramMap.addValue("birthday",user.getDate());
        paramMap.addValue("name",user.getName());
        namedParameterJdbcTemplate.update(sql,paramMap,key);
        return key.getKey().intValue();
    }
    @Override
    public Optional<User> find(long user_id) {
        String sql = "select * from user_info where user_id = :user_id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user_id", user_id);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(sql,mapSqlParameterSource,ROWMAPPER);
            return Optional.of(user);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findByName(String userName) {
        String sql = "select * from user_info where user_name Like :user_name";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user_name", "%"+userName+"%");
        List<User> users = namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,ROWMAPPER);
        return users;
    }

    @Override
    public List<User> find(List<Long> user_ids) {
        String sql = "select * from user_info where user_id IN (:user_ids)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user_ids", user_ids);
        List<User> users = namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,ROWMAPPER);
        return users;
    }

    @Override
    public Optional<User> find(String email) {
        String sql = "select * from user_info where email = :email";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(sql,mapSqlParameterSource,ROWMAPPER);
            return Optional.of(user);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }

    }

    private static final RowMapper<User> ROWMAPPER= (ResultSet rs, int rowNum)->{
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setDate(rs.getDate("birthday"));
        return user;
    };
}