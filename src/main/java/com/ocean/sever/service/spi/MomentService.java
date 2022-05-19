package com.ocean.sever.service.spi;

import com.ocean.sever.config.ResultList;
import com.ocean.sever.entity.Moment;
import com.ocean.sever.entity.User;

import java.util.List;
import java.util.Optional;


/**
 * @author back
 */
public interface MomentService {
    ResultList<Moment> findAllMomentByUserId(long userId, int pageNum);
    ResultList<Moment> findAllMoment(int pageNum, long userId);
    boolean addLikeForAMoment(long userId, long momentId);
    boolean cancelLikeForAMoment(long userId, long momentId);
    List<Long> ifUserLikeThisMoment(long userId);
    long postANewMoment(long userId, String content, String image, int private_level,String author_name);
    Optional<User> getLikeInfo(long userId);
}