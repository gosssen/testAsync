package com.example.testasync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoDto {

    private List<String> employeeNameList;
    private List<String> departmentNameList;

}
