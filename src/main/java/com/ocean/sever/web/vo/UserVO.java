/**
 * @(#)UserVO.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author back
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
    @ApiModelProperty("userId")
    long userId;

    @ApiModelProperty("userName")
    String userName;

    @ApiModelProperty("realName")
    String name;

    @ApiModelProperty("birthday")
    Date birthday;

    @ApiModelProperty("email")
    String email;

}