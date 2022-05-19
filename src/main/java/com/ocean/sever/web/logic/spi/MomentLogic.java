package com.ocean.sever.web.logic.spi;

import com.ocean.sever.config.ResultList;
import com.ocean.sever.entity.User;
import com.ocean.sever.web.vo.MomentVO;

import java.util.Optional;


/**
 * @author back
 */
public interface MomentLogic {
    ResultList<MomentVO> findAllMomentByUserId(long userId, int pageNum);
    ResultList<MomentVO> findAllMoment(int pageNum, long userId);
    boolean addLikeForAMoment(long userId, long momentId);
    boolean cancelLikeForAMoment(long userId, long momentId);
    boolean ifUserLikeThisMoment(long userId, long momentId);
    long postANewMoment(long userId, String content, String image, int private_level, String author_name);
    Optional<User> getLikeInfo(long userId);

}