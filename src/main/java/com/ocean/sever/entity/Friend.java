/**
 * @(#)Friend.java, 5月 13, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author back
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    private long userId;
    private long Id;
    private String userName;
    private boolean block;
}