package com.best11.gamelog.feed.service;

import com.best11.gamelog.feed.dto.PostRequestDto;
import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.entity.Feed;
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
        Feed feedEntity = new Feed(requestDto);
        Feed savePost = feedJpaRepository.save(feedEntity);
        return new PostResponseDto(savePost);
    }
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Feed feedEntity = getPostEntity(postId);
        //verifyPassword(feedEntity, requestDto.getPassword());
        feedEntity.update(requestDto);
        return new PostResponseDto(feedEntity);
    }

    public void deletePost(Long postId, String password) {
        Feed postEntity = getPostEntity(postId);
        //verifyPassword(postEntity, password);
        feedJpaRepository.delete(postEntity);
    }

    private Feed getPostEntity(Long postId) {
        return feedJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post를 찾을 수 없음"));
    }
}
