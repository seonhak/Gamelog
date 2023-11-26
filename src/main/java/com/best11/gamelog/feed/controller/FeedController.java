package com.best11.gamelog.feed.controller;


import com.best11.gamelog.feed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final PostService feedService;

}
