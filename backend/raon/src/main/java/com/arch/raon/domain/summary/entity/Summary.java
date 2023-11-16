package com.arch.raon.domain.summary.entity;

import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.global.util.enums.Grade;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Entity
@Table(name="summary")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE summary SET is_delete = true, deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "is_delete = false")
public class Summary {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id", referencedColumnName = "id")
    private Article article;

    @Column(name = "summary_content", length = 140)
    private String summary_content;

    @Column(name = "feedback_content", length = 1024)
    private String feedback_content;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    private Grade grade;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

}
