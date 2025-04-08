package com.example.HR.Authentication;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByHrUser(HRUser hrUser);
}