package com.best11.gamelog.feed.controller;


import com.best11.gamelog.feed.dto.PostRequestDto;
import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final FeedService feedService;

    @PostMapping("/add")
    public ResponseEntity<PostResponseDto> addPost(
            @RequestBody PostResponseDto requestDto
    ) {
        PostResponseDto responseDto = feedService.addPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto
    ) {
        PostResponseDto responseDto = feedService.updatePost(postId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestHeader("password") String password
    ) {
        feedService.deletePost(postId, password);
        return ResponseEntity.noContent().build();
    }
}
