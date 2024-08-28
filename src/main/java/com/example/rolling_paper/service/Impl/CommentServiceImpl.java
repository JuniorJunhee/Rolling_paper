package com.example.rolling_paper.service.Impl;

import com.example.rolling_paper.dto.CommentDTO;
import com.example.rolling_paper.entity.CommentEntity;
import com.example.rolling_paper.repository.CommentRepository;
import com.example.rolling_paper.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO){
        CommentEntity entity = CommentDTO.toEntity(commentDTO);

        commentRepository.save(entity); //저장하는 순간, DB가 ID를 만들어 entity에 넣어줌
        return getComment(entity.getCommentNo());
    }

    @Override

    public CommentDTO getComment(int idx) {
        Optional<CommentEntity> optional =  commentRepository.findById(idx);

        CommentEntity entity = optional.orElseThrow(()->
                new RuntimeException("not found"));

        return CommentDTO.toDomain(entity);
    }

    @Override
    public CommentDTO updateComment(int idx, CommentDTO commentDTO) {

        CommentEntity existingEntity = commentRepository.findById(idx).orElse(null);

        if (existingEntity != null) {
            // 기존 엔티티를 업데이트
            existingEntity.setContext(commentDTO.getContext());
            existingEntity.setWriter(commentDTO.getWriter());
            // 필요한 다른 필드들도 업데이트

            // 수정된 엔티티를 저장
            commentRepository.save(existingEntity);
        }

        return getComment(idx);
        /*CommentEntity entity = CommentDTO.toEntity(commentDTO);

        commentRepository.save(entity);

        return getComment(entity.getCommentNo());*/
    }

    @Override
    public void deleteComment(int idx) {
        commentRepository.deleteById(idx);
    }

    @Override
    public List<CommentDTO> list() {
        List<CommentEntity> list = commentRepository.findAll();
        List<CommentDTO> result = new ArrayList<>();

        for(CommentEntity comment : list){
            CommentDTO commentDTO = CommentDTO.toDomain(comment);
            result.add(commentDTO);
        }
        return result;
    }

}
