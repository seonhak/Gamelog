package com.best11.gamelog.feed.dto;

import com.best11.gamelog.feed.entity.Feed;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostResponseDto {
    private String title;
    private String author;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public PostResponseDto(Feed feed) {
        this.title = feed.getTitle();
        this.author = feed.getAuthor();
        this.content = feed.getContent();
        this.created_at = feed.getCreatedAt();
        this.updated_at = feed.getUpdatedAt();
    }


}

