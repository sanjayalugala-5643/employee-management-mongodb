package com.example.EmployeeMongoDb.Controller;

import com.example.EmployeeMongoDb.Model.Employee;
import com.example.EmployeeMongoDb.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    @PostMapping("/Employee")
    public String addEmployee(@RequestBody Employee employee) {
        employeeRepo.save(employee);
        return "Add successful";
    }

    @PostMapping("/ListEmployee")
    public String listEmployee(@RequestBody List<Employee> employee) {
        employeeRepo.saveAll(employee);
        return "Add Successful";
    }

    @GetMapping("/Employee/{id}")
    public Employee getEmployee(@PathVariable Long id) {

        return employeeRepo.findById(id).orElse(null);
    }

    @GetMapping("/Employee")
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @DeleteMapping("/Employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepo.deleteById(id);
        return "delete successful";
    }

    @PutMapping("/Employee/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOpt = employeeRepo.findById(id);
        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setJob(updatedEmployee.getJob());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            employeeRepo.save(existingEmployee);
            return "Employee updated successfully!";
        } else {
            return "Employee not found!";
        }
    }

    @PatchMapping("/{id}")
    public String partiallyUpdateEmployee(@PathVariable Long id, @RequestBody Employee partialUpdate) {
        Optional<Employee> existingEmployeeOpt = employeeRepo.findById(id);

        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();

            if (partialUpdate.getName() != null) {
                existingEmployee.setName(partialUpdate.getName());
            }
            if (partialUpdate.getJob() != null) {
                existingEmployee.setJob(partialUpdate.getJob());
            }
            if (partialUpdate.getSalary() != null) {
                existingEmployee.setSalary(partialUpdate.getSalary());
            }

            employeeRepo.save(existingEmployee);
            return "Employee updated successfully!";
        } else {
            return "Employee not found!";
        }
    }
}
