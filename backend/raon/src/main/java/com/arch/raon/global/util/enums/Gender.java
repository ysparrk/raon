package com.arch.raon.global.util.enums;

public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    UNDEFINED("미정");

    private String description;

    Gender(String description) {
        this.description = description;
    }
}
