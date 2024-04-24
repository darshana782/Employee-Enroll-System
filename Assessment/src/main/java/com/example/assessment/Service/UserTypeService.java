package com.example.assessment.Service;

import com.example.assessment.Entity.Branch;
import com.example.assessment.Entity.UserType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserTypeService {
    ResponseEntity<Object> addUserType(String branchName) throws Exception;

    List<UserType> listUserType() throws Exception;
}
