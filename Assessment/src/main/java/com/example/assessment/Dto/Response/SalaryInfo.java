package com.example.assessment.Dto.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryInfo {

    private int employeeId;
    private int salaryId;
    private String employeeName;
    private String salaryPaidMonth;
    private Double amount;
    private String userType;
    private String branch;
    private String status;


}
