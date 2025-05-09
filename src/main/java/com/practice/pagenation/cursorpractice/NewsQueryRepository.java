package com.practice.pagenation.cursorpractice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface NewsQueryRepository {
  List<News> searchNews(
      String keyword,
      UUID interestId,
      List<String> sourceIn,
      Instant publishDateFrom,
      Instant publishDateTo,
      String orderBy,
      String direction,
      String cursor,
      Instant after,
      int limit
  );
}
