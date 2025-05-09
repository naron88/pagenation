package com.practice.pagenation.cursorBased;

import com.practice.pagenation.entity.Department;
import com.practice.pagenation.offsetBased.DepartmentRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursorDepartmentService {
  private final CursorDepartmentRepository departmentRepository;

  public List<Department> getDepartmentsByCursor(Instant cursor, int size) {
    Pageable pageable = PageRequest.of(0, size);
    return departmentRepository.findByCreatedAtAfterOrderByCreatedAtAsc(cursor, pageable);
  }
}
