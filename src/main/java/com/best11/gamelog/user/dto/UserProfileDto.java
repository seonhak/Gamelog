package com.best11.gamelog.user.dto;

import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.entity.Post;
import com.best11.gamelog.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfileDto {
    private String userName;
    private String description;
    private List<PostResponseDto> posts;

    public UserProfileDto(User user){
        this.userName = user.getUsername();
        this.description = user.getDescription();
    }
}
