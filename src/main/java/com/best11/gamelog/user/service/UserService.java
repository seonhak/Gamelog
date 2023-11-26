package com.best11.gamelog.user.service;

import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.entity.Post;
import com.best11.gamelog.user.dto.SignupRequestDto;
import com.best11.gamelog.user.dto.UserRequestDto;
import com.best11.gamelog.user.entity.User;
import com.best11.gamelog.user.jwt.JwtUtil;
import com.best11.gamelog.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public void signup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String passwordCheck = requestDto.getPasswordCheck();
        String description = requestDto.getDescription();

        // userId 중복 체크
        if (userRepository.findByUserId(userId).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 userId 입니다");
        }

        // 비밀번호 확인 일치 체크
        if(!passwordEncoder.matches(passwordCheck, password)){
            throw new RuntimeException("비밀번호를 체크해주세요");
        }

        // username 중복 체크
        if (userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 username 입니다.");
        }

        //사용자 등록
        User user = new User(userId, password, username, description);
        userRepository.save(user);
    }

    public void login(UserRequestDto loginRequestDto) {
        String userId = loginRequestDto.getUserId();
        String password = loginRequestDto.getPassword();
        // userId 검색
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));
        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
