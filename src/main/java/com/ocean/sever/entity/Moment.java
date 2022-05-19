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
public class Moment {
    private long momentId;
    private long authorId;
    private String authorName;
    private String content;
    private String image;
    private int width;
    private int height;
    private int privateLevel;
    private long like;
    private long createTimestamp;
    private long updateTimestamp;
}