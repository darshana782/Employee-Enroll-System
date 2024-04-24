package com.example.assessment.Service.Impl;

import com.example.assessment.Dto.Request.PaymentDto;
import com.example.assessment.Entity.Salary;
import com.example.assessment.Enum.EmployeeStatus;
import com.example.assessment.Dto.Response.SalaryInfo;
import com.example.assessment.Repository.PaymentRepository;
import com.example.assessment.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final JdbcTemplate jdbcTemplate;


    @Override
    @Transactional
    public ResponseEntity<Object> paySalary(PaymentDto paymentDto) throws DataAccessException{

        AtomicReference<ResponseEntity<Object>> responseEntityAtomicReference = new AtomicReference<>();

        try {

            List<Salary> salaries = paymentRepository.getSalaryDetails(paymentDto.getUserType(), EmployeeStatus.ACTIVE.name());

            AtomicReference<Integer> enrollDate = new AtomicReference<>(0);
            AtomicReference<Integer> previousPaidMonth = new AtomicReference<>(0);
            int currentPayMonth = getMonthInNumber(paymentDto.getMonth());

            for (Salary s : salaries) {

                System.out.println(s.getEmployee().getId());

                enrollDate.set(getMonthInNumber(s.getEmployee().getEnrolledDate()));
                previousPaidMonth.set(s.getMonth() != null ? getMonthInNumber(s.getMonth()) : 0);

                if (s.getMonth() == null) {
                    int row = jdbcTemplate.update("UPDATE Salary s SET s.month = ?, s.amount = ?", paymentDto.getMonth().toUpperCase(), Double.parseDouble(paymentDto.getAmount()));

                    responseEntityAtomicReference.set(responseEntityBuild(row));

                } else if (!(s.getMonth().equalsIgnoreCase((paymentDto.getMonth()))) && (enrollDate.get() <= currentPayMonth) && (previousPaidMonth.get() < currentPayMonth)) {
                    int row = jdbcTemplate.update("INSERT INTO Salary (amount, month, employee, user_type) VALUES (?,?,?,?)", Double.parseDouble(paymentDto.getAmount()), paymentDto.getMonth().toUpperCase(), s.getEmployee().getId(), s.getUserType().getId());

                    responseEntityAtomicReference.set(responseEntityBuild(row));

                } else {

                    responseEntityAtomicReference.set(ResponseEntity.badRequest().body("Can't proceed with payments for the given month"));
                }
            }

        } catch (DataAccessException e) {
            log.info("Failed to Update Payments [{}]", e.getMessage());
            throw e;
        }

        return responseEntityAtomicReference.get();

    }


    @Override
    public List<SalaryInfo> paidSalaries(String month) {

        String sql = "SELECT e.id as employee_id, s.id as salary_id, e.name, s.month, s.amount, s.user_type, b.name as branch_name, e.status FROM salary s JOIN employee e ON s.employee = e.id JOIN branch b ON e.branch = b.id WHERE s.month="+"'" + month + "'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SalaryInfo salaryInfo = new SalaryInfo();
            salaryInfo.setEmployeeId(Integer.parseInt(rs.getString("employee_id")));
            salaryInfo.setSalaryId(Integer.parseInt(rs.getString("salary_id")));
            salaryInfo.setEmployeeName(rs.getString("name"));
            salaryInfo.setSalaryPaidMonth(rs.getString("month"));
            salaryInfo.setAmount(Double.valueOf(rs.getString("amount")));
            salaryInfo.setUserType(rs.getString("user_type"));
            salaryInfo.setBranch(rs.getString("branch_name"));
            salaryInfo.setStatus(rs.getString("status"));

            return salaryInfo;
        });

    }

    public ResponseEntity<Object> responseEntityBuild(int row) {
        if (row >= 1) {
            return (ResponseEntity.ok().body("Payment successful"));
        } else {
            return (ResponseEntity.badRequest().body("Payment failed"));
        }
    }

    public int getMonthInNumber(String month) {

        int monthAsNumber = 0;

        switch (month.toLowerCase()) {
            case "january" :
                monthAsNumber = 1;
                break;
            case "february" :
                monthAsNumber = 2;
                break;
            case "march" :
                monthAsNumber = 3;
                break;
            case "april" :
                monthAsNumber = 4;
                break;
            case "may" :
                monthAsNumber = 5;
                break;
            case "june" :
                monthAsNumber = 6;
                break;
            case "july" :
                monthAsNumber = 7;
                break;
            case "august" :
                monthAsNumber = 8;
                break;
            case "september" :
                monthAsNumber = 9;
                break;
            case "october" :
                monthAsNumber = 10;
                break;
            case "november" :
                monthAsNumber = 11;
                break;
            case "december" :
                monthAsNumber = 12;
                break;
            default:
                return 0;

        }
        return monthAsNumber;
    }

}
