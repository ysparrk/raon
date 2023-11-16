package com.arch.raon.global.util.enums;

public enum GrammarRanking {
    GRAMMAR_COUNTRY_MY("전국 맞춤법 퀴즈 개인 랭킹"),
    GRAMMAR_SCHOOL_MY("교내 맞춤법 퀴즈 개인 랭킹");

    private String description;

    GrammarRanking(String description) {
        this.description = description;
    }
}
