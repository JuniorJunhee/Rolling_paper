package com.example.rolling_paper.service;

import com.example.rolling_paper.dto.CommentDTO;
import com.example.rolling_paper.dto.ReplyDTO;
import com.example.rolling_paper.entity.CommentEntity;

import java.util.List;

public interface ReplyService {
    public ReplyDTO addReply(int commentNo, ReplyDTO replyDTO);
    public String deleteReply(int idx);
    public List<ReplyDTO> list(CommentEntity comment);
    List<ReplyDTO> listByCommentNo(int commentNo);
}
