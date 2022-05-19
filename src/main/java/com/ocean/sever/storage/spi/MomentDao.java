/**
 * @(#)MomentDao.java, 4月 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.spi;

import com.ocean.sever.entity.Moment;

import java.util.List;
/**
 * @author back
 */
public interface MomentDao {
    List<Moment> findAllMomentByUserId(long userId);
    List<Moment> findAllMoment(long userId);
    boolean addLikeForAMoment(long userId, long momentId);
    boolean cancelLikeForAMoment(long userId, long momentId);
    List<Long> ifUserLikeThisMoment(long userId);
    long postANewMoment(long userId, String content, String image, int private_level, String author_name);
    Long getLikeInfo(long userId);
}