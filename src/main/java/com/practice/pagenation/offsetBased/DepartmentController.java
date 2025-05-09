package com.practice.pagenation.offsetBased;

import com.practice.pagenation.entity.Department;
import com.practice.pagenation.entity.DepartmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments/offset")
public class DepartmentController {
  private final DepartmentService departmentService;

  @PostMapping
  public ResponseEntity<DepartmentDto> createDepartment(
      @RequestBody DepartmentDto departmentDto
  ) {
    DepartmentDto departmentDto1 = departmentService.createDepartment(departmentDto);
    return ResponseEntity.ok(departmentDto1);
  }

  // http://localhost:8080/departments/offset?name=IT&page=0&size=5&sort=createdAt,asc
  @GetMapping
  public ResponseEntity<PageResponse<DepartmentDto>> getDepartment(
      @RequestParam(name = "name", required = false, defaultValue = "") String name,
      @PageableDefault(
          size = 50,
          page = 0,
          sort = "createdAt",
          direction = Direction.DESC
      ) Pageable pageable) {
    PageResponse<DepartmentDto> departments = departmentService.findByName(name, pageable);
    return ResponseEntity.ok(departments);
  }
}
