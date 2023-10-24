package com.arch.raon.global.util.enums;

public enum Grade {
    GOOD("하"),
    GREAT("중"),
    PERFECT("상");

    private String description;

    Grade(String description) {
        this.description = description;
    }
}
