package com.example.assessment.Service;

import com.example.assessment.Dto.Request.EmployeeDto;
import com.example.assessment.Dto.Request.TerminateEmployeeDto;
import com.example.assessment.Entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    ResponseEntity<Object> enrollUser(EmployeeDto employeeDto) throws Exception;
    List<Employee> listAllEmployees(String userType, String branch) throws Exception;

    ResponseEntity<Object> terminateEmployee(TerminateEmployeeDto terminateEmployeeDto) throws Exception;

    List<Employee> listAllTerminatedEmployees() throws Exception;

    Optional<Employee> emplyeeById(Integer id) throws Exception;
}
