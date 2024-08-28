package com.example.rolling_paper.controller;

import com.example.rolling_paper.dto.CommentDTO;
import com.example.rolling_paper.dto.ReplyDTO;
import com.example.rolling_paper.entity.CommentEntity;
import com.example.rolling_paper.repository.CommentRepository;
import com.example.rolling_paper.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/reply")
public class ReplyController {
    private final ReplyService replyService;
    private final CommentRepository commentRepository;

    @PostMapping("/{commentNo}")
    public ReplyDTO addreply(@PathVariable("commentNo")int commentNo, @RequestBody ReplyDTO replyDTO){
        CommentEntity comment = commentRepository.findById(commentNo).orElseThrow(() -> {
            return new IllegalArgumentException("페이퍼를 찾을 수 없습니다");
        });
        return replyService.addReply(commentNo, replyDTO);
    }

    @DeleteMapping("/delete/{idx}")
    public String deletereply(@PathVariable("idx") int idx){
        replyService.deleteReply(idx);
        return "success";
    }

//    @GetMapping("/list/{commentNo}")
//    public List<ReplyDTO> listItem(@PathVariable("commentNo") int commentNo){
//        CommentEntity comment = new CommentEntity();
//        comment.setCommentNo(commentNo);
//        List<ReplyDTO> replyDTOS = replyService.list(comment);
//        return replyDTOS;
//    }
    @GetMapping("/list/{commentNo}")
    public List<ReplyDTO> listItem(@PathVariable("commentNo") int commentNo) {
        return replyService.listByCommentNo(commentNo);
    }
}
