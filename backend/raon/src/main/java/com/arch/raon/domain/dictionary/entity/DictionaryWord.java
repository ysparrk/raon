package com.arch.raon.domain.dictionary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dictionary_word")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DictionaryWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "word", length = 8, nullable = false)
    private String word;

    @Column(name = "initial", length = 8, nullable = false)
    private String initial;

    @Column(name = "meaning", nullable = false)
    private String meaning;
}
