package com.best11.gamelog.feed.controller;


import com.best11.gamelog.feed.dto.PostRequestDto;
import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.dto.PostUpdateRequestDto;
import com.best11.gamelog.feed.service.PostService;
import com.best11.gamelog.user.security.UserDetailsImpl;
import com.best11.gamelog.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
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
            @RequestBody PostUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            PostResponseDto responseDto = postService.updatePost(postId, requestDto, userDetails.getUser());
            return ResponseEntity.ok(responseDto);
        }catch (RejectedExecutionException | IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new PostResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));

        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            postService.deletePost(postId, userDetails.getUser());
            // 성공 메시지 반환
            return ResponseEntity.status(HttpStatus.OK.value()).body(new CommonResponseDto("삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
