package com.practice.pagenation.cursorBased;

import com.practice.pagenation.entity.Department;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursorDepartmentRepository extends JpaRepository<Department, Long> {
  List<Department> findByCreatedAtAfterOrderByCreatedAtAsc(Instant establishedDate, Pageable pageable);
}
