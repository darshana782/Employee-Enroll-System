package com.example.assessment.Service;

import com.example.assessment.Entity.Branch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BranchService {
    ResponseEntity<Object> addBranch(String branchName) throws Exception;

    List<Branch> listBranches() throws Exception;
}
