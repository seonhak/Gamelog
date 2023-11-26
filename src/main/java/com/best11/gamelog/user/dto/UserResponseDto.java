package com.best11.gamelog.user.dto;

import com.best11.gamelog.CommonResponseDto;
import com.best11.gamelog.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto extends CommonResponseDto {

    private String userId;

    private String password;

    private String username;

    private String description;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.description = user.getDescription();
        this.password = user.getPassword();
        this.username = user.getUsername();
    }

    public UserResponseDto(String msg, Integer status){
        super(msg,status);
    }
}