/**
 * @(#)RequestDaoJdbcImpl.java, 5月 13, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.db;

import com.ocean.sever.entity.Request;
import com.ocean.sever.storage.spi.RequestDao;
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
public class RequestDaoJdbcImpl implements RequestDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<Request> ROWMAPPER= (ResultSet rs, int rowNum)->{
        Request request = new Request();
        request.setId(rs.getLong("id"));
        request.setSource_id(rs.getLong("source_id"));
        request.setDestination_id(rs.getLong("destination_id"));
        return request;
    };


    @Override
    public long addFriendRequest(long sourceId, long destinationId) {
        String sql = "insert into friend_request_info (source_id, destination_id) values (:source_id, :destination_id)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("source_id",sourceId);
        mapSqlParameterSource.addValue("destination_id",destinationId);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Request> getFriendRequest(long destinationId) {
        String sql = "select * from friend_request_info where destination_id = :destination_id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("destination_id",destinationId);
        return namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,ROWMAPPER);
    }

    @Override
    public boolean deleteFriendRequest(long request_id) {
        String sql ="delete from friend_request_info where id = :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",request_id);
        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
        return true;
    }

}