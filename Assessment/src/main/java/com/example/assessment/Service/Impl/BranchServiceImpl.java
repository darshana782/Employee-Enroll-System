package com.example.assessment.Service.Impl;

import com.example.assessment.Dto.Request.EmployeeDto;
import com.example.assessment.Entity.Branch;
import com.example.assessment.Entity.Employee;
import com.example.assessment.Repository.BranchRepository;
import com.example.assessment.Repository.EmployeeRepository;
import com.example.assessment.Repository.UserTypeRepository;
import com.example.assessment.Service.BranchService;
import com.example.assessment.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public ResponseEntity<Object> addBranch(String branchName) throws Exception {
        if (branchRepository.findBy(branchName) != null) {
            return ResponseEntity.badRequest().body("Branch is already added");
        } else {

            try {

                branchRepository.save(Branch.builder().branchName(branchName).build());
                return ResponseEntity.ok().body("Branch Added Succcessfully");

            } catch (Exception e) {
                log.error("ERROR OCCURRED WHEN ADDING BRANCH :: [{}]", e.getMessage());
                return ResponseEntity.badRequest().body("Branch Added Failed");
            }
        }
    }

    @Override
    public List<Branch> listBranches() throws Exception {
        return branchRepository.findAll();
    }
}
