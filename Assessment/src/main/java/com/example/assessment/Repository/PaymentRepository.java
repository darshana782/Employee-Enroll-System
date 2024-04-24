package com.example.assessment.Repository;

import com.example.assessment.Entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Salary, Integer> {

    @Query(value = "SELECT s FROM Salary s JOIN Employee e ON s.employee.id = e.id WHERE e.userType.type=?1 AND e.status=?2 ORDER BY s.employee.id DESC")
    List<Salary> getSalaryDetails(String userType, String status);

    @Query(value = "SELECT s FROM Salary s WHERE s.month=?1")
    List<Salary> getSalaryInfoByMonth(String month);
}
