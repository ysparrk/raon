package com.arch.raon.domain.member.dto.response;

import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.global.util.enums.Gender;
import com.arch.raon.global.util.enums.School;
import lombok.Getter;

@Getter
public class MemberResDto {

    private Long id;
    private String email;
    private String nickname;
    private String profileUrl;
    private Integer yearOfBirth;
    private Gender gender;
    private School school;
    private Integer mileage;
    private Boolean isDeleted;

    public static MemberResDto of(Member member) {
        MemberResDto memberResDto = new MemberResDto();
        memberResDto.id = member.getId();
        memberResDto.email = member.getEmail();
        memberResDto.nickname = member.getNickname();
        memberResDto.profileUrl = member.getProfileUrl();
        memberResDto.yearOfBirth = member.getYearOfBirth();
        memberResDto.gender = member.getGender();
        memberResDto.school = member.getSchool();
        memberResDto.mileage = member.getMileage();
        memberResDto.isDeleted = member.getIsDeleted();

        return memberResDto;
    }

}
