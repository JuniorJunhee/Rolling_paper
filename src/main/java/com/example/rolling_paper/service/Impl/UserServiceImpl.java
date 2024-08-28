package com.example.rolling_paper.service.Impl;

import com.example.rolling_paper.dto.UserDTO;
import com.example.rolling_paper.entity.UserEntity;
import com.example.rolling_paper.repository.UserRepository;
import com.example.rolling_paper.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO add(UserDTO user) {
        UserEntity entity = UserDTO.toEntity(user);
        String encodedPassword = passwordEncoder.encode(user.getPasswd());
        entity.setPasswd(encodedPassword);
        userRepository.save(entity);
        entityManager.clear();
        return get(entity.getUserId());
    }

    @Override
    public UserDTO get(int idx) {
        UserEntity entity = userRepository.findById(idx).orElseThrow();
        return UserDTO.toDomain(entity);
    }

    @Override
    public UserDTO get(String email) {
        UserEntity entity = userRepository.findByEmail(email).orElseThrow();
        return UserDTO.toDomain(entity);
    }

    @Override
    public UserDTO update(UserDTO user) {
        UserEntity entity = UserDTO.toEntity(user);
        userRepository.save(entity);
        return get(entity.getUserId());
    }

    @Override
    public void remove(int userIdx) {
        userRepository.deleteById(userIdx);
    }
}