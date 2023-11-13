package com.arch.raon.domain.member.dto.response;

import com.arch.raon.domain.member.entity.School;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberCheckSchoolResDTO {
    private List<School> schoolNameList;

    @Builder
    public MemberCheckSchoolResDTO(List<School> schoolNameList) {
        this.schoolNameList = schoolNameList;
    }

    public MemberCheckSchoolResDTO() {
    }
}
