package com.best11.gamelog.feed.controller;


import com.best11.gamelog.feed.dto.PostRequestDto;
import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.service.PostService;
import com.best11.gamelog.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("printUser" + userDetails.getUser().getUsername());
        PostResponseDto responseDto = postService.addPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> responseDto = postService.getPosts();
        return ResponseEntity.ok(responseDto);
    }
    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        PostResponseDto responseDto = postService.updatePost(postId, requestDto, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.deletePost(postId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
