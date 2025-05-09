package com.practice.pagenation.cursorpractice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;
  private String summary;

  private UUID interestId;
  private String source;

  private Instant publishDate;

  @CreatedDate
  private Instant createdAt;

  public News(String title, String summary, UUID interestId, String source, Instant publishDate) {
    this.title = title;
    this.summary = summary;
    this.interestId = interestId;
    this.source = source;
    this.publishDate = publishDate;
  }
}
