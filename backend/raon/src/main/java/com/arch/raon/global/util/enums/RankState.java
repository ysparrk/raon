package com.arch.raon.global.util.enums;

public enum RankState {
    TOP3("TOP3 순위"),

    MIDDLE_PLACE("중간 순위"),
    LAST_PLACE("최하위 순위");

    private String description;

    RankState(String description) {
        this.description = description;
    }
}

