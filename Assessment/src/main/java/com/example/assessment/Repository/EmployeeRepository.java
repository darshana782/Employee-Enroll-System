package com.example.assessment.Repository;

import com.example.assessment.Entity.Employee;
import com.example.assessment.Entity.Salary;
import com.example.assessment.Entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT x FROM Employee x WHERE x.email = ?1 OR x.mobile = ?2")
    Employee findByEmailAndMobile (String email, String mobile);

    @Query(value = "SELECT x FROM Employee x WHERE x.userType.type = ?1 OR x.branch.branchName = ?2")
    List<Employee> findAllByUserTypeORBranch(String userType, String branchName);

    @Query(value = "SELECT x FROM Employee x WHERE x.userType.type = ?1 AND x.branch.branchName = ?2")
    List<Employee> findAllByUserTypeAndBranch(String userType, String branchName);

    @Query(value = "UPDATE Employee x SET x.status = ?2 WHERE x.id = ?1")
    Employee updateEmployee(int id, String status);

    @Query(value = "SELECT x FROM Employee x WHERE x.status = ?1")
    List<Employee> findAllTerminated(String status);

    @Query(value = "SELECT x FROM Employee x WHERE x.userType = ?1 AND x.status = ?2")
    List<Employee> findAllByUserTypeAndStatus(UserType userType, String status);

    @Query(value = "SELECT x FROM Employee x WHERE x.userType = ?1 AND x.status = ?2")
    List<Employee> findByActiveUserType(String userType, String status);

//    @Query(value = "SELECT e FROM Employee e INNER JOIN Salary s ON e.id = s.employee.id WHERE e.userType.type=?1 AND e.status=?2")
//    List<Employee> getSalaryDetailsOfEmployees(String userType, String status);
}
