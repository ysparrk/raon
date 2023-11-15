package com.arch.raon.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberCheckSchoolReqDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    private String keyword;

    public MemberCheckSchoolReqDTO() {
    }

    public MemberCheckSchoolReqDTO(@NotNull String keyword) {
        this.keyword = keyword;
    }
}
