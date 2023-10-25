package com.arch.raon.domain.member.dto.request;

import com.arch.raon.global.util.enums.Gender;
import com.arch.raon.global.util.enums.School;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberReqDto {

    private String nickname;
    private School school;
    private Integer year_of_birth;
    private Gender gender;

    @Builder
    public CreateMemberReqDto(String nickname, School school, Integer year_of_birth, Gender gender) {
        this.nickname = nickname;
        this.school = school;
        this.year_of_birth = year_of_birth;
        this.gender = gender;
    }
}
