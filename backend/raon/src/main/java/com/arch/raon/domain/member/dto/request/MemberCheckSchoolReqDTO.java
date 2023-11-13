package com.arch.raon.domain.member.dto.request;

import lombok.Getter;

@Getter
public class MemberCheckSchoolReqDTO {
    private String keyword;

    public MemberCheckSchoolReqDTO() {
    }

    public MemberCheckSchoolReqDTO(String keyword) {
        this.keyword = keyword;
    }
}
