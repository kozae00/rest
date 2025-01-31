package com.example.rest.domain.post.post.entity;

import com.example.rest.domain.member.member.entity.Member;
import com.example.rest.global.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Post extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    private String title;
    private String content;
}