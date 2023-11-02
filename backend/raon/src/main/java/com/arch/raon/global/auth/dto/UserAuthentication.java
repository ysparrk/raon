package com.arch.raon.global.auth.dto;

import lombok.Getter;

@Getter
public class UserAuthentication {
    private String id;

    public UserAuthentication(String id){
        this.id = id;
    }
}