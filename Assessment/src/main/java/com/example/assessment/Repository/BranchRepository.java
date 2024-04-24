package com.example.assessment.Repository;

import com.example.assessment.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Query(value = "SELECT x FROM Branch x WHERE x.branchName = ?1")
    Branch findBy(String branchName);
}
