package com.example.rolling_paper.dto;

import com.example.rolling_paper.entity.CommentEntity;
import com.example.rolling_paper.entity.ReplyEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private int replyNo;
    private String writer;
    private String context;
    private int commentNo;

    public static ReplyEntity toEntity(ReplyDTO replyDTO){
        return ReplyEntity.builder()
                .replyNo(replyDTO.getReplyNo())
                .writer(replyDTO.getWriter())
                .context(replyDTO.getContext())
                .build();
    }

    public static ReplyDTO toDomain(ReplyEntity replyEntity){
        return ReplyDTO.builder()
                .replyNo(replyEntity.getReplyNo())
                .writer(replyEntity.getWriter())
                .context(replyEntity.getContext())
                .commentNo(replyEntity.getComment().getCommentNo())
                .build();
    }
}
