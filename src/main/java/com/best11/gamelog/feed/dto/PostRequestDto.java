package com.best11.gamelog.feed.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto{
    private String title;
    private String content;
}
