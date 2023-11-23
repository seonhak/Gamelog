package com.best11.gamelog.feed.repository;

import com.best11.gamelog.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByOrderByCreatedAtDesc();
}
