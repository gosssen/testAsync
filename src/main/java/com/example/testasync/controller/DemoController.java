package com.example.testasync.controller;

import com.example.testasync.dto.*;
import com.example.testasync.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/*              |                                                           |
                |                                                           |
                |                               @Async   async-2            |   http://localhost:8080/demo/department
                |                              +------------------------+   |   +------------------------+
                | http://localhost:8080  +---->| DemoService            |------>| DepartmentController   |
                |       /demo/get       /      |   .getDepartmentData() |   |   |   .getDepartmentData() |
 +----------+   |   +------------------+       +------------------------+   |   +------------------------+
 |  Client  |------>| DemoController   |                                    |
 |          |   |   |   .getBasicInfo()|        @Async   async-1            |   http://localhost:8080/demo/employee
 +----------+   |   +------------------+       +------------------------+   |   +------------------------+
                |                       \      | DemoService            |------>| EmployeeController     |
                |                        +---->|   .getEmployeeData()   |   |   |   .getEmployeeData()   |
                |                              +------------------------+   |   +------------------------+
                |                                                           |
                |                                                           |
*/

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "/get")
    public BasicInfoDto getBasicInfo() throws InterruptedException, ExecutionException {

        CompletableFuture<EmployeeDataDto> employeeDataDtoCompletableFuture =
                demoService.getEmployeeData();
        CompletableFuture<DepartmentDataDto> departmentDataDtoCompletableFuture =
                demoService.getDepartmentData();

        CompletableFuture.allOf(
                employeeDataDtoCompletableFuture,
                departmentDataDtoCompletableFuture
        ).join();

        List<EmployeeDto> employeeDtoList =
                employeeDataDtoCompletableFuture.get().getEmployeeDtoList();
        List<DepartmentDto> departmentDtoList =
                departmentDataDtoCompletableFuture.get().getDepartmentDtoList();

        return new BasicInfoDto(
                employeeDtoList.stream().map(EmployeeDto::getName)
                        .collect(Collectors.toList()),
                departmentDtoList.stream().map(DepartmentDto::getName)
                        .collect(Collectors.toList()));

    }

}
