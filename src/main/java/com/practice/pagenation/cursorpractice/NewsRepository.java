package com.practice.pagenation.cursorpractice;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, UUID>, NewsQueryRepository {

}
