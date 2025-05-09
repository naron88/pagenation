package com.practice.pagenation.cursorpractice;

import java.time.Instant;
import java.util.UUID;

public record NewsDto(
    UUID id,
    String title,
    String summary,
    Instant publishDate
) {

}
