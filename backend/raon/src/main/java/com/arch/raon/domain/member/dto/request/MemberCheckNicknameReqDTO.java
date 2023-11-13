package com.arch.raon.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCheckNicknameReqDTO {
    private String nickname;

    public MemberCheckNicknameReqDTO() {
    }

    @Builder
    public MemberCheckNicknameReqDTO(String nickname) {
        this.nickname = nickname;
    }
}
