package com.arch.raon.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCheckNicknameReqDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[가-힣0-9]{2,12}$", message = "2글자 이상 12글자 이하의 한글 혹은 한글 + 숫자 조합만 가능합니다.")
    private String nickname;

    public MemberCheckNicknameReqDTO() {
    }

    @Builder
    public MemberCheckNicknameReqDTO(@NotNull String nickname) {
        this.nickname = nickname;
    }
}
