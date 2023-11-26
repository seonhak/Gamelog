package com.best11.gamelog.feed.dto;

import com.best11.gamelog.feed.entity.Post;
import com.best11.gamelog.CommonResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostResponseDto extends CommonResponseDto {
    private String title;
    private String author;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.created_at = post.getCreatedAt();
        this.updated_at = post.getUpdatedAt();
    }

    public PostResponseDto(String msg, Integer statuscode){
        super(msg,statuscode);

    }}

