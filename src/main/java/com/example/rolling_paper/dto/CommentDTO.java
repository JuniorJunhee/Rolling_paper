package com.example.rolling_paper.dto;

import com.example.rolling_paper.annotation.ForbiddenWords;
import com.example.rolling_paper.annotation.KoreanLength;
import com.example.rolling_paper.entity.CommentEntity;
import lombok.*;

@Getter @Setter @ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int commentNo;
    private String writer;
    @KoreanLength(max = 100, message = "한글 기준으로 최대 100글자까지 입력할 수 있습니다.")
    @ForbiddenWords(words = {"금지어1", "금지어2", "금지어3"}, message = "메시지에 금지어가 포함되어 있습니다.")
    private String context;
    private Boolean isPublic;
    private Boolean isCheck;
    private Boolean isKeep;

    public static CommentEntity toEntity(CommentDTO comment){
        return CommentEntity.builder()
                .commentNo(comment.getCommentNo())
                .writer(comment.getWriter())
                .context(comment.getContext())
                .isPublic(comment.getIsPublic())
                .isCheck(comment.getIsCheck())
                .isKeep(comment.getIsKeep())
                .build();
    }

    public static CommentDTO toDomain(CommentEntity comment){
        return CommentDTO.builder()
                .commentNo(comment.getCommentNo())
                .writer(comment.getWriter())
                .context(comment.getContext())
                .isPublic(comment.getIsPublic())
                .isCheck(comment.getIsCheck())
                .isKeep(comment.getIsKeep())
                .build();
    }
}
