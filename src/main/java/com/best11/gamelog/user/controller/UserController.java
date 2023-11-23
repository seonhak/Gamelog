package com.best11.gamelog.user.controller;

import com.best11.gamelog.user.service.UserService;
import com.best11.gamelog.CommonResponseDto;
//import com.best11.gamelog.user.dto.DescriptionRequestDto;
import com.best11.gamelog.user.dto.SignupRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // Validation 예외 처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            throw new IllegalArgumentException("error");
        }
        // 회원 가입 시도
        try {
            userService.signup(signupRequestDto);
        }
        // 실패 메시지 반환
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        // 성공 메시지 반환
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

//    @PutMapping("/description")
//    public ResponseEntity<CommonResponseDto> updateDescription(DescriptionRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        userService.updateDescription(requestDto, userDetails.getUser());
//
//        return ResponseEntity.status(HttpStatus.OK.value()).body(new CommonResponseDto("변경이 완료되었습니다.", HttpStatus.OK.value()));
//    }
}
