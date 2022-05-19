/**
 * @(#)UserDaoImpl.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.db;

import com.ocean.sever.entity.User;
import com.ocean.sever.storage.spi.UseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

/**
 * @author back
 */
@Repository
public class UserDaoJdbcTemplateImpl implements UseDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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