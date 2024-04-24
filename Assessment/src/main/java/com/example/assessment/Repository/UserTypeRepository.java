package com.example.assessment.Repository;

import com.example.assessment.Entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    @Query(value = "SELECT x FROM UserType x WHERE x.type = ?1")
    UserType findBy(String userType);
}
