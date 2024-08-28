package com.example.rolling_paper.controller;

import com.example.rolling_paper.dto.CommentDTO;
import com.example.rolling_paper.dto.UserDTO;
import com.example.rolling_paper.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add")
    public CommentDTO addComment(@Valid @RequestBody CommentDTO commentDTO){
        return commentService.addComment(commentDTO);
    }

    @GetMapping("/get/{idx}")
    public CommentDTO getItem(@PathVariable(value = "idx") int idx){
        return commentService.getComment(idx);
    }

    @PutMapping("/update/{idx}")
    public CommentDTO updateComment(@PathVariable(value = "idx") int idx, @RequestBody CommentDTO commentDTO){
        return commentService.updateComment(idx, commentDTO);
    }

    @DeleteMapping("delete/{idx}")
    public String DeleteComment(@PathVariable(value = "idx") int idx){
        commentService.deleteComment(idx);
        return "success";
    }

    @GetMapping("/list")
    public List<CommentDTO> listItem(){
        return commentService.list();
    }

}
