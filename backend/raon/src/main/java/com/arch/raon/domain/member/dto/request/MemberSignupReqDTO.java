package com.arch.raon.domain.member.dto.request;

import com.arch.raon.global.util.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignupReqDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[가-힣0-9]{2,12}$", message = "2글자 이상 12글자 이하의 한글 혹은 한글 + 숫자 조합만 가능합니다.")
    private String nickname;
    @NotNull
    @NotBlank
    @NotEmpty
    private String school;
    @NotNull
    @Min(value = 1900)
    @Max(value = 2024)
    private int yearOfBirth;
    @NotNull
    private Gender gender;

    public MemberSignupReqDTO() {
    }

    @Builder
    public MemberSignupReqDTO(@NotNull String nickname, @NotNull String school, @NotNull int yearOfBirth, @NotNull Gender gender) {
        this.nickname = nickname;
        this.school = school;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MemberSignupReqDTO{" +
                "nickname='" + nickname + '\'' +
                ", school=" + school +
                ", yearOfBirth=" + yearOfBirth +
                ", gender=" + gender +
                '}';
    }
}
