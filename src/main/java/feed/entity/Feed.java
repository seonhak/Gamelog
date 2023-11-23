package feed.entity;

import feed.dto.FeedRequestDto;
import feed.dto.FeedResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "feeds")
@NoArgsConstructor
public class Feed extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String content;

    public Feed(FeedRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }

    public Feed(FeedResponseDto responseDto) {
        this.title = responseDto.getTitle();
        this.author = responseDto.getAuthor();
        this.content = responseDto.getContent();
    }
}