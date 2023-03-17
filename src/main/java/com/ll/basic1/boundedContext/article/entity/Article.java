package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)  // 데이터 생성 시간 및 마지막 수정 시간을 넣기 위한 어노테이션
public class Article {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = IDENTITY)    // AUTO_INCREMENT
    private long id;
    @CreatedDate    // 자동으로 데이터 생성 시간을 컬럼에 넣어줌
    private LocalDateTime createDate;
    @LastModifiedDate   // 자동으로 마지막 데이터 수정 시간을 컬럼에 넣어줌
    private LocalDateTime modifyDate;
    private String title;
    private String body;
}
