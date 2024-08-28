package com.example.rolling_paper.repository;

import com.example.rolling_paper.entity.CommentEntity;
import com.example.rolling_paper.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {
    List<ReplyEntity> findAllByComment(CommentEntity comment);
}
