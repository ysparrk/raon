package com.arch.raon.global.auth.enums;

import lombok.Getter;

@Getter
public enum RoleType {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String description;

    RoleType(String description) {
        this.description = description;
    }
}
