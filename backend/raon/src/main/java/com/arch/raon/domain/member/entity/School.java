package com.arch.raon.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "school")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "school", length = 256, nullable = false)
    private String schoolName;

    @Column(name = "location", length = 256, nullable = false)
    private String location;

    @Builder
    public School(Long id, String schoolName, String location) {
        this.id = id;
        this.schoolName = schoolName;
        this.location = location;
    }
}
