package com.best11.gamelog.feed.entity;

import com.best11.gamelog.feed.dto.PostRequestDto;
import com.best11.gamelog.feed.dto.PostResponseDto;
import com.best11.gamelog.feed.dto.PostUpdateRequestDto;
import com.best11.gamelog.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "feeds")
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String content;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequestDto resquestDto) {
        this.title = resquestDto.getTitle();
        this.author = resquestDto.getAuthor();
        this.content = resquestDto.getContent();
    }

    public void update(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}