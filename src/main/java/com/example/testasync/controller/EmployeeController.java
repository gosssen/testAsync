package com.example.testasync.controller;


import com.example.testasync.dto.EmployeeDataDto;
import com.example.testasync.dto.EmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/employee")
    public EmployeeDataDto getEmployeeData() {
        List<EmployeeDto> employeeDtoList = Arrays.asList(
                new EmployeeDto(1L, "john"),
                new EmployeeDto(2L, "gary"));

        return new EmployeeDataDto(employeeDtoList);
    }
}
