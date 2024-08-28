package com.example.rolling_paper.controller;

import com.example.rolling_paper.config.JwtProvider;
import com.example.rolling_paper.dto.CommentDTO;
import com.example.rolling_paper.dto.LoginRequest;
import com.example.rolling_paper.dto.LoginResponse;
import com.example.rolling_paper.dto.UserDTO;
import com.example.rolling_paper.entity.UserEntity;
import com.example.rolling_paper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;


    @PostMapping("")
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO user) {
        UserDTO saved = userService.add(user);

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {

        AbstractAuthenticationToken abstractAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword());

        log.info("zzzzz {}  {}", loginRequest.getEmail(), loginRequest.getPassword());

        LoginResponse response = LoginResponse.builder().build();
        try {
            Authentication authentication =
                    authenticationManager.authenticate(abstractAuthenticationToken);

            log.info("[LOGIN] authentication - {}", authentication);
            log.info("  principal - {}", authentication.getPrincipal());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // JWT을 생성한다.
            String jwt = jwtProvider.createToken(authentication);

            response.setSuccess(true);
            response.setToken(jwt);
        } catch (BadCredentialsException e) {
            response.setSuccess(false);
            response.setMessage("등록되지 않은 이메일이거나, 비밀번호가 올바르지 않습니다.");
        } catch (AccountExpiredException e) {
            response.setSuccess(false);
            response.setMessage("탈퇴한 계정입니다.");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

//    @PostMapping("/join")
//    public String joinUser(@RequestBody UserDTO userDTO){
//        return userService.joinUser(userDTO);
//    }
//
//    @PostMapping("/login")
//    public String loginUser(@RequestBody Map<String, String> requestBody){
//        return userService.loginUser(requestBody.get("userId"), requestBody.get("passwd"));
//    }
}
