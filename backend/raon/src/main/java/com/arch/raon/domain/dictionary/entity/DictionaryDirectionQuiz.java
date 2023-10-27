package com.arch.raon.domain.dictionary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "dictionary_direction_quiz")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE dictionary_direction_quiz SET deleted_at = now() WHERE dictionary_direction_quiz_id = ?")
@Getter
public class DictionaryDirectionQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "west_word", length = 16, nullable = false)
    private String westWord;
    @Column(name = "north_word", length = 16, nullable = false)
    private String northWord;
    @Column(name = "east_word", length = 16, nullable = false)
    private String eastWord;
    @Column(name = "south_word", length = 16, nullable = false)
    private String southWord;
    @Column(name = "answer", length = 8, nullable = false)
    private String answer;
}
