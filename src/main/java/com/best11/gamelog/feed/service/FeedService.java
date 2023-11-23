package com.best11.gamelog.feed.service;

import com.best11.gamelog.feed.dto.PostRequestDto;
import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.entity.Post;
import com.best11.gamelog.feed.repository.FeedJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedJpaRepository feedJpaRepository;
    public PostResponseDto addPost(PostResponseDto requestDto) {
        // Dto -> Entity
        Post postEntity = new Post(requestDto);
        Post savePost = feedJpaRepository.save(postEntity);
        return new PostResponseDto(savePost);
    }
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post postEntity = getPostEntity(postId);
        //verifyPassword(feedEntity, requestDto.getPassword());
        postEntity.update(requestDto);
        return new PostResponseDto(postEntity);
    }

    public void deletePost(Long postId, String password) {
        Post postEntity = getPostEntity(postId);
        //verifyPassword(postEntity, password);
        feedJpaRepository.delete(postEntity);
    }

    private Post getPostEntity(Long postId) {
        return feedJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post를 찾을 수 없음"));
    }
}
