package com.example.assessment.Service.Impl;

import com.example.assessment.Entity.Branch;
import com.example.assessment.Entity.UserType;
import com.example.assessment.Repository.BranchRepository;
import com.example.assessment.Repository.UserTypeRepository;
import com.example.assessment.Service.BranchService;
import com.example.assessment.Service.UserTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    @Override
    public ResponseEntity<Object> addUserType(String userType) throws Exception {
        if (userTypeRepository.findBy(userType) != null) {
            return ResponseEntity.badRequest().body("userType is already added");
        } else {

            try {

                userTypeRepository.save(UserType.builder().type(userType).build());
                return ResponseEntity.ok().body("userType Added Succcessfully");

            } catch (Exception e) {
                log.error("ERROR OCCURRED WHEN ADDING UserType :: [{}]", e.getMessage());
                return ResponseEntity.badRequest().body("userType Added Failed");
            }
        }
    }

    @Override
    public List<UserType> listUserType() throws Exception {
        return userTypeRepository.findAll();
    }

}
