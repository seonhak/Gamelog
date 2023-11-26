package com.best11.gamelog.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private String userId;

    private String password;

    private String username;

    private String description;
}