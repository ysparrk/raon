package com.arch.raon.global.util.enums;

public enum ArticleCategory {
    SOCIAL("사회"),
    LIFE("생활"),
    SCIENCE("과학"),
    WORLD("세계"),
    SPORTS("스포츠");

    private String description;

    ArticleCategory(String description) {
        this.description = description;
    }
}
