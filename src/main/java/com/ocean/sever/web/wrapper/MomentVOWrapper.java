/**
 * @(#)MomentVOWrapper.java, 4月 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.wrapper;


import com.ocean.sever.entity.Moment;
import com.ocean.sever.web.vo.MomentVO;


/**
 * @author back
 */
public class MomentVOWrapper {

    public static MomentVO wrapper (Moment moment){
        if (moment == null){
            return null;
        }
        MomentVO vo = MomentVO.builder().momentId(moment.getMomentId())
                .authorName(moment.getAuthorName())
                .image(moment.getImage())
                .content(moment.getContent())
                .createTimestamp(moment.getCreateTimestamp())
                .like(moment.getLike())
                .privateLevel(moment.getPrivateLevel())
                .width(moment.getWidth())
                .height(moment.getHeight()).build();
        return vo;
    }
}