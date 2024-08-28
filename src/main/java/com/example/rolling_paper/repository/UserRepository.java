package com.example.rolling_paper.repository;

import com.example.rolling_paper.dto.UserDTO;
import com.example.rolling_paper.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//리포지토리는 데이터 소스와 비지니스 로직 사이의 추상화 계층을 제공.
//이를 통해 데이터 접근 로직과 비즈니스 로직이 분리되어, 어플리케이션의 유지보수성과 확장성이 향상

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserId(int userId);
    Optional<UserEntity> findByEmail(String email);
}
