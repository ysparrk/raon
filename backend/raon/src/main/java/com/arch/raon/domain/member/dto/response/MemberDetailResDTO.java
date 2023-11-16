package com.arch.raon.domain.member.dto.response;

import com.arch.raon.global.util.enums.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDetailResDTO {
    private String profileUrl;
    private String nickname;
    private String school;
    private int yearOfBirth;
    private Gender gender;

    @Builder
    public MemberDetailResDTO(String profileUrl, String nickname, String school, int yearOfBirth, Gender gender) {
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.school = school;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
    }

    public MemberDetailResDTO() {
    }
}
