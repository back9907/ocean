/**
 * @(#)defineNum.java, 4月 30, 2022.
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
public enum defineNum {
    MOMENT_ME_PAGE_SIZE (9,"page size of moment in me"),
    MAIN_MOMENT_PAGE_SIZE(15,"page size of moment in main page")
    ;

    private int value;

    private String name;

    defineNum(int value, String name) {
        this.value = value;
        this.name = name;
    }


    public static Optional<defineNum> findByInt(int value) {
        for (defineNum item : defineNum.values()) {
            if (item.value == value) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    public static Optional<defineNum> findByString(String name) {
        for (defineNum item : defineNum.values()) {
            if (item.name.equals(name)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    @JsonCreator
    public static defineNum findNullableByString(String name) {
        for (defineNum item : defineNum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }

        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.name;
    }

    public int toInt() {
        return this.value;
    }
}
