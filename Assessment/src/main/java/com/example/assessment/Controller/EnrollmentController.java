package com.example.assessment.Controller;

import com.example.assessment.Dto.Request.EmployeeDto;
import com.example.assessment.Dto.Request.PaymentDto;
import com.example.assessment.Dto.Request.TerminateEmployeeDto;
import com.example.assessment.Entity.Branch;
import com.example.assessment.Entity.Employee;
import com.example.assessment.Entity.UserType;
import com.example.assessment.Dto.Response.SalaryInfo;
import com.example.assessment.Service.BranchService;
import com.example.assessment.Service.EmployeeService;
import com.example.assessment.Service.PaymentService;
import com.example.assessment.Service.UserTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("enrollment")
public class EnrollmentController {

    private EmployeeService employeeService;

    private BranchService branchService;

    private UserTypeService userTypeService;

    private PaymentService paymentService;

    public EnrollmentController(EmployeeService employeeService, BranchService branchService, UserTypeService userTypeService, PaymentService paymentService) {
        this.employeeService = employeeService;
        this.branchService = branchService;
        this.userTypeService = userTypeService;
        this.paymentService = paymentService;
    }

    //add branch
    @PostMapping(path = "/addBranch")
    public ResponseEntity<Object> addBranch(@RequestParam String branchName) throws Exception {
        return branchService.addBranch(branchName);
    }

    //list all branches
    @GetMapping(path = "/listBranches")
    public List<Branch> listBranches() throws Exception {
        return branchService.listBranches();
    }

    //add user type
    @PostMapping(path = "/addUserType")
    public ResponseEntity<Object> addUserType(@RequestParam String userType) throws Exception {
        return userTypeService.addUserType(userType);
    }

    //list all UserTypes
    @GetMapping(path = "/userTypes")
    public List<UserType> listUserTypes() throws Exception {
        return userTypeService.listUserType();
    }

    //enroll user
    @PostMapping(path = "/enroll")
    public ResponseEntity<Object> enrollUser(@RequestBody EmployeeDto employeeDto) throws Exception {
        return employeeService.enrollUser(employeeDto);
    }

    @GetMapping(path = "/listAllEmployees")
    public List<Employee> listAllEmployees(@RequestParam @Nullable String userType, @RequestParam @Nullable String branch) throws Exception {
        return employeeService.listAllEmployees(userType, branch);
    }

    @PutMapping(path = "/terminateEmployee")
    public ResponseEntity<Object> terminateEmployee(@RequestBody TerminateEmployeeDto terminateEmployeeDto) throws Exception {
        return employeeService.terminateEmployee(terminateEmployeeDto);
    }

    @GetMapping(path = "/listAllTerminatedEmployees")
    public List<Employee> listAllTerminatedEmployees() throws Exception {
        return employeeService.listAllTerminatedEmployees();
    }

    @GetMapping(path = "/emplyeeById")
    public Optional<Employee> emplyeeById(@RequestParam Integer id) throws Exception {
        return employeeService.emplyeeById(id);
    }

    @PostMapping(path = "/paySalary")
    public ResponseEntity<Object> paySalary(@RequestBody PaymentDto paymentDto) throws Exception {
        return paymentService.paySalary(paymentDto);
    }

    @GetMapping(path = "/paidSalaries")
    public List<SalaryInfo> paidSalaries(@RequestParam String month) throws Exception {
        return paymentService.paidSalaries(month);
    }
}
