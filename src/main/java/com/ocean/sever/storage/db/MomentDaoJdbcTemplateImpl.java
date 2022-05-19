package com.ocean.sever.storage.db;


import com.ocean.sever.entity.Moment;
import com.ocean.sever.storage.spi.MomentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * @author back
 */
@Repository
public class MomentDaoJdbcTemplateImpl implements MomentDao {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Moment> findAllMomentByUserId(long userId) {
        List<Moment> moments;
        try {
            String sql = "select * from moment_info where author_id = :author_id order by create_time desc";
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("author_id", userId);
            moments = namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,ROWMAPPER);
            return moments;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Moment> findAllMoment(long userId) {
        List<Moment> moments;
        try {
            String sql = "select distinct * from moment_info where private_level = 1 \n" +
                    "or (author_id = :user_id and private_level = 3) \n" +
                    "or (author_id in (select distinct friend_id from user_relation where user_id = :user_id) and private_level = 2) or author_id = :user_id order by create_time desc ";
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("user_id",userId);
            moments = namedParameterJdbcTemplate.query(sql,mapSqlParameterSource, ROWMAPPER);
            return moments;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addLikeForAMoment(long userId,long momentId) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "update moment_info set like_num = like_num +1 where moment_id = :moment_id";
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("moment_id", momentId);
            namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

            String sql2 = "insert into like_info (user_id, moment_id) values (:user_id, :moment_id)";
            MapSqlParameterSource mapSqlParameterSource1 = new MapSqlParameterSource();
            mapSqlParameterSource1.addValue("user_id",userId);
            mapSqlParameterSource1.addValue("moment_id",momentId);
            namedParameterJdbcTemplate.update(sql2,mapSqlParameterSource1,keyHolder);

            platformTransactionManager.commit(status);
             return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public boolean cancelLikeForAMoment(long userId, long momentId) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "update moment_info set like_num = like_num -1 where moment_id = :moment_id";
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("moment_id", momentId);
            namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

            String sql2 = "delete from like_info where user_id = :user_id and moment_id = :moment_id";
            MapSqlParameterSource mapSqlParameterSource1 = new MapSqlParameterSource();
            mapSqlParameterSource1.addValue("user_id",userId);
            mapSqlParameterSource1.addValue("moment_id",momentId);
            namedParameterJdbcTemplate.update(sql2,mapSqlParameterSource1,keyHolder);

            platformTransactionManager.commit(status);
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public List<Long> ifUserLikeThisMoment(long userId) {
        String sql = "select moment_id from like_info where user_id = :user_id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user_id",userId);
        return namedParameterJdbcTemplate.queryForList(sql,mapSqlParameterSource,Long.class);
    }

    @Override
    public long postANewMoment(long userId, String content, String image, int private_level, String author_name) {
        String sql = "insert into moment_info (author_id,content,image,private_level,author_name) values (:author_id, :content, :image, :private_level, :author_name)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("author_id",userId);
        mapSqlParameterSource.addValue("content",content);
        mapSqlParameterSource.addValue("image",image);
        mapSqlParameterSource.addValue("private_level",private_level);
        mapSqlParameterSource.addValue("author_name",author_name);
        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Long> getLikeInfo(long userId) {
        String sql = "select like_info.user_id from like_info join moment_info on like_info.moment_id = moment_info.moment_id " +
                " WHERE author_id = :author_id ORDER BY like_info.create_time DESC limit 1";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("author_id",userId);
//        long Id = namedParameterJdbcTemplate.queryForObject(sql,mapSqlParameterSource,Long.class);
        return Optional.of(1L);
    }

    private static final RowMapper<Moment> ROWMAPPER= (ResultSet rs, int rowNum)->{
        Moment moment = new Moment();
        moment.setMomentId(rs.getLong("moment_id"));
        moment.setAuthorId(rs.getLong("author_id"));
        moment.setAuthorName(rs.getString("author_name"));
        moment.setContent(rs.getString("content"));
        moment.setImage(rs.getString("image"));
        moment.setPrivateLevel(rs.getInt("private_level"));
        moment.setHeight(rs.getInt("height"));
        moment.setLike(rs.getLong("like_num"));
        moment.setWidth(rs.getInt("width"));
        moment.setCreateTimestamp(rs.getTimestamp("create_time").getTime());
        moment.setUpdateTimestamp(rs.getTimestamp("modify_time").getTime());
        return moment;
    };
}