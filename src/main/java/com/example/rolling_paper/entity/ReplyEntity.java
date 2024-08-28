package com.example.rolling_paper.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private int replyNo;

    @Column(nullable = false, length = 15)
    private String writer;

    @Column(nullable = false, length = 200)
    private String context;

    @ManyToOne
    @JoinColumn(name = "comment_no")
    private CommentEntity comment;

}
