package com.practice.pagenation.cursorpractice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

  private final NewsService newsService;

  @GetMapping
  public ResponseEntity<List<NewsDto>> getNewsList(
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) UUID interestId,
      @RequestParam(required = false) List<String> sourceIn,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant publishDateFrom,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant publishDateTo,
      @RequestParam(defaultValue = "publishDate") String orderBy,
      @RequestParam(defaultValue = "DESC") String direction,
      @RequestParam(required = false) String cursor,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant after,
      @RequestParam(defaultValue = "50") int limit,
      @RequestHeader("Monew-Request-User-ID") UUID userId
  ) {
    List<NewsDto> results = newsService.getNewsList(
        keyword, interestId, sourceIn, publishDateFrom, publishDateTo,
        orderBy, direction, cursor, after, limit, userId
    );
    return ResponseEntity.ok(results);
  }
}
