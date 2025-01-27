package com.example.rest.domain.post.post.dto;

import com.example.rest.domain.post.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private long id;
    @JsonIgnore
    private LocalDateTime createdDate;
    @JsonIgnore
    private LocalDateTime modifiedDate;
    private String title;
    private String content;
    public PostDto(Post post) {
        this.id = post.getId();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public LocalDateTime getCreatedAt() {
        return createdDate;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedDate;
    }
}
