package com.example.assessment.Service;

import com.example.assessment.Dto.Request.PaymentDto;
import com.example.assessment.Dto.Response.SalaryInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {

    ResponseEntity<Object> paySalary(PaymentDto paymentDto);


    List<SalaryInfo> paidSalaries(String month);
}
