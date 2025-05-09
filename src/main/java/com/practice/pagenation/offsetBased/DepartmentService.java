package com.practice.pagenation.offsetBased;

import com.practice.pagenation.entity.Department;
import com.practice.pagenation.entity.DepartmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {
  private final DepartmentRepository departmentRepository;
  private final PageResponseMapper pageResponseMapper;

  public PageResponse<DepartmentDto> findByName(String name, Pageable pageable) {
    Page<DepartmentDto> departments = departmentRepository.findAllByName(name, pageable)
        .map(this::convertToDTO);
    return pageResponseMapper.fromPage(departments);
  }

  private DepartmentDto convertToDTO(Department department) {
    return new DepartmentDto(
        department.getId(),
        department.getName(),
        department.getDescription(),
        department.getCreatedAt()
    );
  }

  public DepartmentDto createDepartment(DepartmentDto departmentDto) {
    Department department = new Department(departmentDto.name(), departmentDto.description());
    departmentRepository.save(department);
    return departmentDto;
  }
}
