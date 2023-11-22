package feed.dto;

import feed.entity.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedResponseDto {
    private String title;
    private String author;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public FeedResponseDto(Feed feed) {
        this.title = feed.getTitle();
        this.author = feed.getAuthor();
        this.content = feed.getContent();
        this.created_at = feed.getCreatedAt();
        this.updated_at = feed.getUpdatedAt();
    }
}

