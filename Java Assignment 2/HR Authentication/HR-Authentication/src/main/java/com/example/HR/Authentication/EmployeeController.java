package com.example.HR.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable Long id,
            @CookieValue(name = "HR_SESSION", required = false) String sessionId) {

        HRUser currentUser = HRController.activeSessions.get(sessionId);
        if (currentUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) return ResponseEntity.notFound().build();

        Employee employee = employeeOpt.get();
        if (!employee.getHrUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @CookieValue(name = "HR_SESSION", required = false) String sessionId) {

        HRUser currentUser = HRController.activeSessions.get(sessionId);
        if (currentUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(employeeRepository.findByHrUser(currentUser));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee,
            @CookieValue(name = "HR_SESSION", required = false) String sessionId) {

        HRUser currentUser = HRController.activeSessions.get(sessionId);
        if (currentUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        employee.setHrUser(currentUser);
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeDetails,
            @CookieValue(name = "HR_SESSION", required = false) String sessionId) {

        HRUser currentUser = HRController.activeSessions.get(sessionId);
        if (currentUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) return ResponseEntity.notFound().build();

        Employee employee = employeeOpt.get();
        if (!employee.getHrUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (employeeDetails.getName() != null) employee.setName(employeeDetails.getName());
        if (employeeDetails.getDepartment() != null) employee.setDepartment(employeeDetails.getDepartment());
        if (employeeDetails.getEmail() != null) employee.setEmail(employeeDetails.getEmail());
        if (employeeDetails.getSalary() != 0) employee.setSalary(employeeDetails.getSalary());

        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(
            @PathVariable Long id,
            @CookieValue(name = "HR_SESSION", required = false) String sessionId) {

        HRUser currentUser = HRController.activeSessions.get(sessionId);
        if (currentUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) return ResponseEntity.notFound().build();

        Employee employee = employeeOpt.get();
        if (!employee.getHrUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}