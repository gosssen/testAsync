package com.example.testasync.controller;

import com.example.testasync.dto.DepartmentDataDto;
import com.example.testasync.dto.DepartmentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DepartmentController {

    @GetMapping("/department")
    public DepartmentDataDto getDepartmentData() {
        List<DepartmentDto> departmentDtoList = Arrays.asList(
                new DepartmentDto(1L, "Marketing"),
                new DepartmentDto(2L, "HR")
        );
        return new DepartmentDataDto(departmentDtoList);
    }
}
