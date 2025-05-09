package com.practice.pagenation.entity;

import java.time.Instant;

public record DepartmentDto(
    Long id,
    String name,
    String description,
    Instant createdAt
) {

}
