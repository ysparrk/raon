package com.arch.raon.domain.dictionary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "dictionary_initial_quiz")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE dictionary_initial_quiz SET deleted_at = now() WHERE dictionary_initial_quiz_id = ?")
public class DictionaryInitialQuiz {

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
