package com.ocean.sever.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author back
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long userId;
    private String userName;
    private String name;
    private String password;
    private Date date;
    private String email;
}