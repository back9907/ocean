/**
 * @(#)MomentLogicImpl.java, 4月 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.logic.Impl;

import com.ocean.sever.config.ResultList;
import com.ocean.sever.entity.Moment;
import com.ocean.sever.entity.User;
import com.ocean.sever.service.spi.MomentService;
import com.ocean.sever.web.logic.spi.MomentLogic;
import com.ocean.sever.web.vo.MomentVO;
import com.ocean.sever.web.wrapper.MomentVOWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author back
 */
@Service
public class MomentLogicImpl implements MomentLogic {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    MomentService momentService;

    @Override
    public ResultList<MomentVO> findAllMomentByUserId(long userId, int pageNum) {
        ResultList <Moment>resultList = momentService.findAllMomentByUserId(userId,pageNum);
        Integer total = resultList.getTotal();
        List<Moment> moments = resultList.getList();
        List<MomentVO> momentVOS = moments.stream().map(MomentVOWrapper::wrapper).collect(Collectors.toList());
        ResultList<MomentVO> result = new ResultList<>();
        result.setList(momentVOS);
        result.setTotal(total);
        return result;
    }

    @Override
    public ResultList<MomentVO> findAllMoment(int pageNum, long userId) {
        ResultList <Moment>resultList = momentService.findAllMoment(pageNum, userId);
        Integer total = resultList.getTotal();
        List<Moment> moments = resultList.getList();
        List<MomentVO> momentVOS = moments.stream().map(MomentVOWrapper::wrapper).collect(Collectors.toList());
        ResultList<MomentVO> result = new ResultList<>();
        result.setList(momentVOS);
        result.setTotal(total);
        return result;
    }

    @Override
    public boolean addLikeForAMoment(long userId,long momentId) {
        return momentService.addLikeForAMoment(userId,momentId);
    }

    @Override
    public boolean cancelLikeForAMoment(long userId, long momentId) {
        return momentService.cancelLikeForAMoment(userId,momentId);
    }

    @Override
    public boolean ifUserLikeThisMoment(long userId, long momentId) {
        List<Long> moments = momentService.ifUserLikeThisMoment(userId);
        return moments.contains(momentId);
    }

    @Override
    public long postANewMoment(long userId, String content, String image,int private_level, String author_name) {
        return momentService.postANewMoment(userId, content, image, private_level,author_name);
    }

    @Override
    public Optional<User> getLikeInfo(long userId) {
        return momentService.getLikeInfo(userId);
    }
}