package com.example.assessment.Dto.Request;

import com.example.assessment.Entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String name;
    private String address;
    private String email;
    private String mobile;
    private String userType;
    private String branchName;
}
