package com.ocean.sever.service.Impl;

import com.ocean.sever.config.Page;
import com.ocean.sever.config.ResultList;
import com.ocean.sever.constant.defineNum;
import com.ocean.sever.entity.Moment;
import com.ocean.sever.entity.User;
import com.ocean.sever.service.spi.MomentService;
import com.ocean.sever.service.spi.UserService;
import com.ocean.sever.storage.db.MomentDaoJdbcTemplateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author back
 */
@Service
public class MomentServiceImpl implements MomentService {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    MomentDaoJdbcTemplateImpl momentDaoJdbcTemplate;

    @Autowired
    UserService userService;

    @Override
    public ResultList<Moment> findAllMomentByUserId(long userId, int pageNum) {
        return Page.Page(pageNum, defineNum.MOMENT_ME_PAGE_SIZE.toInt(), momentDaoJdbcTemplate.findAllMomentByUserId(userId), true);
    }

    @Override
    public ResultList<Moment> findAllMoment(int pageNum, long userId) {
        return Page.Page(pageNum, defineNum.MAIN_MOMENT_PAGE_SIZE.toInt(), momentDaoJdbcTemplate.findAllMoment(userId), true);
    }

    @Override
    public boolean addLikeForAMoment(long userId, long momentId) {
        return momentDaoJdbcTemplate.addLikeForAMoment(userId, momentId);
    }

    @Override
    public boolean cancelLikeForAMoment(long userId, long momentId) {
        return momentDaoJdbcTemplate.cancelLikeForAMoment(userId, momentId);
    }

    @Override
    public List<Long> ifUserLikeThisMoment(long userId) {
        return momentDaoJdbcTemplate.ifUserLikeThisMoment(userId);
    }

    @Override
    public long postANewMoment(long userId, String content, String image, int private_level, String author_name) {
        return momentDaoJdbcTemplate.postANewMoment(userId, content, image, private_level, author_name);
    }

    @Override
    public Optional<User> getLikeInfo(long userId) {
        return userService.findUserByUserId(momentDaoJdbcTemplate.getLikeInfo(userId));
    }

}