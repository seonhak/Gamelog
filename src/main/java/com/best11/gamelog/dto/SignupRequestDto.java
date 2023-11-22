package com.best11.gamelog.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Size(min = 4,max = 10, message ="아이디는 4자 이상 10자 이하만 가능합니다.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "아이디는 알파벳 소문자, 숫자만 사용가능합니다.")
    private String userId;

    @Size(min = 8,max = 15, message ="비밀번호는 8자 이상 15자 이하만 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z_0-9]*$", message = "비밀번호는 알파벳 대소문자, 숫자만 사용가능합니다.")
    private String password;

    private String passwordCheck;

//    @Pattern()
    private String username;

//    @Pattern()
    private String description;
}
