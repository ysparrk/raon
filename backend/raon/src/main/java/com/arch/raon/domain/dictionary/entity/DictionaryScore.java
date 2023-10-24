package com.arch.raon.domain.dictionary.entity;

import com.arch.raon.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dictionary_score")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DictionaryScore extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "score", nullable = false, columnDefinition = "0")
    private Integer score;
}
