/**
 * @(#)CommonCode.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

/**
 * @author back
 */
public enum CommonCode {
    SUCCESS(200,"success"),
    INNER_ERROR(500,"Internal server error"),
    OUTTER_ERROR(400,"External server error"),
    NO_AUTHORITY(403,"Authentication failed"),
    NO_USER(404,"User does not exist");

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    CommonCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Optional<CommonCode> findByInt(int value) {
        for (CommonCode item : CommonCode.values()) {
            if (item.code == value) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public static Optional<CommonCode> findByString(String name) {
        for (CommonCode item : CommonCode.values()) {
            if (item.message.equals(name)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    @JsonCreator
    public static CommonCode findNullableByString(String name) {
        for (CommonCode item : CommonCode.values()) {
            if (item.message.equals(name)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.message;
    }

    public int toInt() {
        return this.code;
    }
}