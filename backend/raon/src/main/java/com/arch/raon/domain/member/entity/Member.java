package com.arch.raon.domain.member.entity;

import com.arch.raon.global.auth.enums.AuthProvider;
import com.arch.raon.global.auth.enums.RoleType;
import com.arch.raon.global.auth.oauth2.OAuth2UserInfo;
import com.arch.raon.global.util.enums.Gender;
import com.arch.raon.global.util.enums.School;
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

    @Column(name = "nickname", length = 10, nullable = false)
    private String nickname;

    @Column(name = "profile_url", length = 255, nullable = false)
    private String profileUrl;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "school")
    @Enumerated(EnumType.STRING)
    private School school;

    @Column(name = "year_of_birth", nullable = false)
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
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "oauth2_id")
    private String oauth2Id;  // 로그인시 전달되는 id

    @Column(name = "auth_provider")
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;  // adimin or user

    public Member update(OAuth2UserInfo oAuth2UserInfo) {
        this.nickname = oAuth2UserInfo.getName();
        this.oauth2Id = oAuth2UserInfo.getOAuth2Id();

        return this;
    }

    @Builder
    public Member(Long id, String email, String nickname, String profileUrl, Gender gender, School school, Integer yearOfBirth, Integer mileage, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean isDeleted, LocalDateTime deletedAt, String oauth2Id, AuthProvider authProvider, RoleType roleType) {
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
        this.oauth2Id = oauth2Id;
        this.authProvider = authProvider;
        this.roleType = roleType;
    }
}
