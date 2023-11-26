package com.best11.gamelog.feed.dto;

import com.best11.gamelog.feed.entity.Timestamped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto{
    private String title;
    private String author;
    private String content;
}
