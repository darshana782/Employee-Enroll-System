package com.example.assessment.Service.Impl;

import com.example.assessment.Dto.Request.EmployeeDto;
import com.example.assessment.Dto.Request.TerminateEmployeeDto;
import com.example.assessment.Entity.Branch;
import com.example.assessment.Entity.Employee;
import com.example.assessment.Entity.Salary;
import com.example.assessment.Entity.UserType;
import com.example.assessment.Enum.EmployeeStatus;
import com.example.assessment.Repository.BranchRepository;
import com.example.assessment.Repository.EmployeeRepository;
import com.example.assessment.Repository.PaymentRepository;
import com.example.assessment.Repository.UserTypeRepository;
import com.example.assessment.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserTypeRepository userTypeRepository;
    private final BranchRepository branchRepository;

    private final PaymentRepository paymentRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public ResponseEntity<Object> enrollUser(EmployeeDto employeeDto) throws Exception {

        if (employeeRepository.findByEmailAndMobile(employeeDto.getEmail(), employeeDto.getMobile()) != null) {
            return ResponseEntity.badRequest().body("User already registered");
        } else {

            try {

                UserType userType = userTypeRepository.findBy(employeeDto.getUserType());
                Branch branch = branchRepository.findBy(employeeDto.getBranchName());

                Employee employee = new Employee();
                Salary salary = new Salary();

                employee.setName(employeeDto.getName());
                employee.setAddress(employeeDto.getAddress());
                employee.setEmail(employeeDto.getEmail());
                employee.setMobile(employeeDto.getMobile());
                employee.setStatus(EmployeeStatus.ACTIVE.name());
                employee.setUserType(userType);
                employee.setBranch(branch);

                LocalDate currentDate = LocalDate.now();

                employee.setEnrolledDate(String.valueOf(currentDate.getMonth()));

                Employee save = employeeRepository.save(employee);

                salary.setEmployee(save);
                salary.setUserType(save.getUserType());

                paymentRepository.save(salary);

                return ResponseEntity.ok().body("User Added Succcessfully");

            } catch (Exception e) {
                log.error("ERROR OCCURRED WHEN ENROLLING USER :: [{}]", e.getMessage());
                return ResponseEntity.badRequest().body("User Added Failed");
            }
        }

    }

    @Override
    public List<Employee> listAllEmployees(String userType, String branch) throws Exception {

        List<Employee> employees;

        if (!userType.isEmpty() && !branch.isEmpty()) {
            employees = employeeRepository.findAllByUserTypeAndBranch(userType, branch);
        } else if (!userType.isEmpty() || !branch.isEmpty()) {
            employees = employeeRepository.findAllByUserTypeORBranch(userType, branch);
        } else {
            employees = employeeRepository.findAll();
        }

        return employees;
    }

    @Override
    public ResponseEntity<Object> terminateEmployee(TerminateEmployeeDto terminateEmployeeDto) throws DataAccessException {
        try {

            int row = 0;
            if (terminateEmployeeDto.getStatus().equals("Terminate")) {
                row = jdbcTemplate.update("UPDATE Employee x SET x.status = ? WHERE x.id = ?", EmployeeStatus.TERMINATED.name(), terminateEmployeeDto.getId());
            }

            if (row >= 1) {
                return ResponseEntity.ok("Employee teminated successfully");
            } else {
                return ResponseEntity.badRequest().body("Employee temination failed");
            }

        } catch (DataAccessException e) {
            log.info("Failed to Update As Terminate [{}]", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Employee> listAllTerminatedEmployees() throws Exception{
        return employeeRepository.findAllTerminated(EmployeeStatus.TERMINATED.name());
    }

    @Override
    public Optional<Employee> emplyeeById(Integer id) throws Exception{
        return employeeRepository.findById(id);
    }

}
