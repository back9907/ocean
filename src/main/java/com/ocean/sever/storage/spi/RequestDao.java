/**
 * @(#)RequestDao.java, 5月 13, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.storage.spi;

import com.ocean.sever.entity.Request;

import java.util.List;

/**
 * @author back
 */
public interface RequestDao {
    long addFriendRequest(long sourceId, long destinationId);
    List<Request> getFriendRequest(long destinationId);
    boolean deleteFriendRequest(long request_id);
}