package com.arch.raon.domain.member.dto.request;

import com.arch.raon.global.util.enums.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignupReqDTO {
    private String nickname;
    private String school;
    private Integer yearOfBirth;
    private Gender gender;

    public MemberSignupReqDTO() {
        super();
    }

    @Builder
    public MemberSignupReqDTO(String nickname, String school, Integer yearOfBirth, Gender gender) {
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
