package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = IDENTITY)    // AUTO_INCREMENT
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String title;
    private String body;
}
