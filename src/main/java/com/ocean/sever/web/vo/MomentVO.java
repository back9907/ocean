package com.ocean.sever.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author back
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MomentVO {
    @ApiModelProperty("moment id")
    private long momentId;

    @ApiModelProperty("author name")
    private String authorName;

    @ApiModelProperty("moment content")
    private String content;

    @ApiModelProperty("image url")
    private String image;

    @ApiModelProperty("width of image")
    private int width;

    @ApiModelProperty("height of image")
    private int height;

    @ApiModelProperty("moment private Level")
    private int privateLevel;

    @ApiModelProperty("the number of like")
    private long like;

    @ApiModelProperty("the moment create time")
    private long createTimestamp;
}