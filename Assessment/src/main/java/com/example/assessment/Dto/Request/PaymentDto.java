package com.example.assessment.Dto.Request;

import com.example.assessment.Entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String month;
    private String amount;
    private String userType;
}
