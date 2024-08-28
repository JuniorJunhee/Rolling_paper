package com.example.rolling_paper.service.Impl;

import com.example.rolling_paper.dto.MyUserDetails;
import com.example.rolling_paper.dto.UserDTO;
import com.example.rolling_paper.entity.UserEntity;
import com.example.rolling_paper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity entity = userRepository.findByEmail(username).orElseThrow();
            UserDTO user = UserDTO.toDomain(entity);

            return new MyUserDetails(user);

        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
