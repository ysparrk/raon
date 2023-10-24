package com.arch.raon.global.util.enums;

public enum School {
    ARCH("아치초등학교"),
    INDEOK("인덕초등학교"),
    YOUNGSEO("영서초등학교");

    private String description;

    School(String description) {
        this.description = description;
    }
}