package com.best11.gamelog.feed.service;

import com.best11.gamelog.feed.dto.PostRequestDto;
import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.entity.Post;
import com.best11.gamelog.feed.repository.FeedJpaRepository;
import com.best11.gamelog.user.UserDetailsImpl;
import com.best11.gamelog.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final FeedJpaRepository feedJpaRepository;
    public PostResponseDto addPost(PostRequestDto requestDto,User user) {
        // Dto -> Entity
        Post postEntity = new Post(requestDto);
        postEntity.setUser(user);
        Post savePost = feedJpaRepository.save(postEntity);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long postId) {
        Post postEntity = getPostEntity(postId);
        return new PostResponseDto(postEntity);
    }

    public List<PostResponseDto> getPosts() {
        return feedJpaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post postEntity = getUserPost(postId,user);
        postEntity.update(requestDto);
        return new PostResponseDto(postEntity);
    }

    public void deletePost(Long postId, User user) {
        Post postEntity = getUserPost(postId,user);
        feedJpaRepository.delete(postEntity);
    }

    private Post getPostEntity(Long postId) {
        return feedJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post를 찾을 수 없음"));
    }

    private Post getUserPost(Long postId, User user){
        Post post = getPostEntity(postId);
        if(!user.getId().equals(post.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return post;
    }

}
