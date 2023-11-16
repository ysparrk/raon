package com.arch.raon.domain.member.entity;

import com.arch.raon.domain.member.dto.request.MemberSignupReqDTO;
import com.arch.raon.global.util.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE member SET deleted_at = now() WHERE member_id = ?")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "nickname", length = 12, nullable = false)
    private String nickname;

    @Column(name = "profile_url", length = 255)
    private String profileUrl;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "school")
    private String school;

    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @ColumnDefault("0")
    @Column(name = "mileage", nullable = false)
    private Integer mileage;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private Boolean isDeleted=false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ColumnDefault("false")
    @Column(name = "is_active")
    private Boolean isActive=false; // 기본 정보 입력 후 회원 가입 확정

    @Builder
    public Member(Long id, String email, String nickname, String profileUrl, Gender gender, String school,
                  Integer yearOfBirth, Integer mileage, LocalDateTime createdAt, LocalDateTime modifiedAt,
                  Boolean isDeleted, LocalDateTime deletedAt, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.gender = gender;
        this.school = school;
        this.yearOfBirth = yearOfBirth;
        this.mileage = mileage;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
        this.isActive = isActive;
    }

    public void signup(MemberSignupReqDTO memberSignupReqDTO){
        this.nickname = memberSignupReqDTO.getNickname();
        this.school = memberSignupReqDTO.getSchool();
        this.yearOfBirth = memberSignupReqDTO.getYearOfBirth();
        this.gender = memberSignupReqDTO.getGender();
        this.isActive = Boolean.TRUE;
    }
}
