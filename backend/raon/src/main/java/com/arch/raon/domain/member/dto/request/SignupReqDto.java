package com.arch.raon.domain.member.dto.request;

import com.arch.raon.global.util.enums.Gender;
import com.arch.raon.global.util.enums.School;
import lombok.Builder;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class SignupReqDto implements Serializable {

    private String email;
    private String nickname;
    private String profileUrl;
    private School school;
    private Gender gender;
    private Integer yearOfBirth;

    public SignupReqDto(){
        super();
    }

    @Builder
    public SignupReqDto(String email, String nickname, String profileUrl, School school, Gender gender, Integer yearOfBirth) {
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.school = school;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "SignupReqDto{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", school=" + school +
                ", gender=" + gender +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
