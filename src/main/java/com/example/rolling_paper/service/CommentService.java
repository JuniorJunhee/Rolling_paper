package com.example.rolling_paper.service;

import com.example.rolling_paper.dto.CommentDTO;
import com.example.rolling_paper.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    public CommentDTO addComment(CommentDTO commentDTO);
    public CommentDTO getComment(int idx);
    public CommentDTO updateComment(int idx, CommentDTO commentDTO);
    public void deleteComment(int idx);
    public List<CommentDTO> list();
}
