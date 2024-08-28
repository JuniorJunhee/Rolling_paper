package com.example.rolling_paper.service.Impl;

import com.example.rolling_paper.dto.ReplyDTO;
import com.example.rolling_paper.entity.CommentEntity;
import com.example.rolling_paper.entity.ReplyEntity;
import com.example.rolling_paper.repository.CommentRepository;
import com.example.rolling_paper.repository.ReplyRepository;
import com.example.rolling_paper.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    private final CommentRepository commentRepository;

    @Override
    public ReplyDTO addReply(int commentNo, ReplyDTO replyDTO) {
        CommentEntity comment = commentRepository.findById(commentNo).orElseThrow(()->{
            return new IllegalArgumentException("페이퍼를 찾을 수 없습니다");
        });
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setContext(replyDTO.getContext());
        replyEntity.setWriter(replyDTO.getWriter());
        replyEntity.setComment(comment);

        replyRepository.save(replyEntity);

        replyDTO.setReplyNo(replyEntity.getReplyNo());
        replyDTO.setCommentNo(comment.getCommentNo()); // commentNo 설정

        //return ReplyDTO.toDomain(replyEntity);
        return replyDTO;
    }

    @Override
    public String deleteReply(int idx) {
        replyRepository.deleteById(idx);
        return "success";
    }

    @Override
    public List<ReplyDTO> list(CommentEntity comment) {
        List<ReplyEntity> replyEntities = replyRepository.findAllByComment(comment);
        List<ReplyDTO> replyDTOs = new ArrayList<>();

        replyEntities.forEach(replyEntity -> replyDTOs.add(ReplyDTO.toDomain(replyEntity)));
        return replyDTOs;
    }
    @Override
    public List<ReplyDTO> listByCommentNo(int commentNo) {
        CommentEntity comment = commentRepository.findById(commentNo).orElseThrow(() -> {
            return new IllegalArgumentException("페이퍼를 찾을 수 없습니다");
        });
        return list(comment);
    }
}
