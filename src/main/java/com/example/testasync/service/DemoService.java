package com.example.testasync.service;

import com.example.testasync.dto.DepartmentDataDto;
import com.example.testasync.dto.EmployeeDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class DemoService {

    private final RestTemplate restTemplate;

    @Autowired
    public DemoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async("executor")
    public CompletableFuture<EmployeeDataDto> getEmployeeData() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ":get employee data start");

        EmployeeDataDto employeeDataDto = restTemplate
                .getForObject("http://localhost:8080/demo/employee", EmployeeDataDto.class);

        Thread.sleep(5000);
        System.out.println(threadName + ":get employee data end");

        return CompletableFuture.completedFuture(employeeDataDto);
    }

    @Async("executor")
    public CompletableFuture<DepartmentDataDto> getDepartmentData() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ":get department data start");

        DepartmentDataDto departmentDataDto = restTemplate
                .getForObject("http://localhost:8080/demo/department", DepartmentDataDto.class);

        Thread.sleep(1000);
        System.out.println(threadName + ":get department data end");
        return CompletableFuture.completedFuture(departmentDataDto);
    }

}