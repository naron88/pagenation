package com.practice.pagenation.cursorpractice;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
  NewsDto toDto(News news);
}
