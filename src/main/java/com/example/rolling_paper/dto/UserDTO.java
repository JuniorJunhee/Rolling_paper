package com.example.rolling_paper.dto;

import com.example.rolling_paper.entity.UserEntity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class UserDTO {
    private int userId;
    private String passwd;
    private String nickname;
    private String email;

    public static UserEntity toEntity(UserDTO domain) {
        return UserEntity.builder()
                .userId(domain.getUserId())
                .passwd(domain.getPasswd())
                .nickname(domain.getNickname())
                .email(domain.getEmail())
                .build();
    }

    public static UserDTO toDomain(UserEntity entity) {
        return UserDTO.builder()
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .passwd(entity.getPassword())
                .build();
    }

}
