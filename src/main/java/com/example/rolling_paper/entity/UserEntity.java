package com.example.rolling_paper.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, length = 60)
    private String passwd;

    @Column(nullable = false, length = 10)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired(){
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked(){
        return true; // true -> 잠금되지 않음
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired(){
        return true; // true -> 만료되지 않음
    }

    // 계정 사용 가능 여부 변환
    @Override
    public boolean isEnabled(){
        return true; // true -> 사용 가능
    }
}
