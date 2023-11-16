package com.arch.raon.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class CheckActiveResDTO implements Serializable {
    private Boolean active;
    private String nickname;

    public CheckActiveResDTO() { super(); }

    @Builder
    public CheckActiveResDTO(Boolean active, String nickname) {
        this.active = active;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "CheckActiveResDTO{" +
                "active=" + active +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
