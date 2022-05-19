/**
 * @(#)ChatMessage.java, 5月 20, 2022.
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
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private long id;
    private long senderID;
    private long receiverId;
    private String message;
    private long time;
}