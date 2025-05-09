package com.practice.pagenation.cursorBased;

import com.practice.pagenation.entity.Department;
import com.practice.pagenation.offsetBased.DepartmentService;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments/cursor")
@RequiredArgsConstructor
public class CursorDepartmentCursorController {
  private final CursorDepartmentService departmentService;

  @GetMapping
  public ResponseEntity<List<Department>> getDepartments(
      @RequestParam(required = false) String cursor,
      @RequestParam(defaultValue = "5") int size
  ) {
    Instant cursorInstant = cursor != null ? Instant.parse(cursor) : null;
    List<Department> departments = departmentService.getDepartmentsByCursor(cursorInstant, size);
    return ResponseEntity.ok(departments);
  }
}