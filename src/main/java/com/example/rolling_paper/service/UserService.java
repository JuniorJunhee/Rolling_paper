package com.example.rolling_paper.service;

import com.example.rolling_paper.dto.UserDTO;
import com.example.rolling_paper.entity.UserEntity;

public interface UserService {
//    public String joinUser(UserDTO userDTO);
//
//    public String loginUser(String userId, String passwd);
    UserDTO add(UserDTO user);
    UserDTO get(int idx);
    UserDTO get(String email);
    UserDTO update(UserDTO userDTO);
    void remove(int userIdx);
}
