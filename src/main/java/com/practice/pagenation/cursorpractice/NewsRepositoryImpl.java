package com.practice.pagenation.cursorpractice;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsQueryRepository {

  private final JPAQueryFactory queryFactory;
  private final QNews news = QNews.news;

  @Override
  public List<News> searchNews(String keyword, UUID interestId, List<String> sourceIn,
      Instant publishDateFrom, Instant publishDateTo, String orderBy, String direction,
      String cursor, Instant after, int limit) {

    BooleanBuilder where = new com.querydsl.core.BooleanBuilder();

    if (keyword != null) {
      where.and(news.title.containsIgnoreCase(keyword)
          .or(news.summary.containsIgnoreCase(keyword)));
    }
    if (interestId != null) {
      where.and(news.interestId.eq(interestId));
    }
    if (sourceIn != null && !sourceIn.isEmpty()) {
      where.and(news.source.in(sourceIn));
    }
    if (publishDateFrom != null) {
      where.and(news.publishDate.goe(publishDateFrom));
    }
    if (publishDateTo != null) {
      where.and(news.publishDate.loe(publishDateTo));
    }
    if (after != null) {
      where.and(news.createdAt.lt(after));
    }

    OrderSpecifier<?> orderSpecifier = getOrderSpecifier(orderBy, direction, news);

    return queryFactory.selectFrom(news).where(where).orderBy(orderSpecifier).limit(limit).fetch();
  }

  private OrderSpecifier<?> getOrderSpecifier(String orderBy, String direction, QNews news) {
    Order order = direction.equalsIgnoreCase("ASC") ? Order.ASC : Order.DESC;
    return switch (orderBy) {
      case "publishDate" -> new OrderSpecifier<>(order, news.publishDate);
      default -> new OrderSpecifier<>(order, news.createdAt);
    };
  }
}

