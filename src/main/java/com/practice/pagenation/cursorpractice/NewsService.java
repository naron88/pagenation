package com.practice.pagenation.cursorpractice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

  private final NewsRepository newsRepository;
  private final NewsMapper newsMapper;

  public List<NewsDto> getNewsList(
      String keyword,
      UUID interestId,
      List<String> sourceIn,
      Instant publishDateFrom,
      Instant publishDateTo,
      String orderBy,
      String direction,
      String cursor,
      Instant after,
      int limit,
      UUID userId
  ) {
    return newsRepository.searchNews(
        keyword, interestId, sourceIn, publishDateFrom, publishDateTo,
        orderBy, direction, cursor, after, limit
    ).stream().map(newsMapper::toDto).collect(Collectors.toList());
  }
}

