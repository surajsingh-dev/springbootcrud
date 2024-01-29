package com.MyProject.springboot.controller;

import com.MyProject.springboot.model.Employee;
import com.MyProject.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/employees")
    private List<Employee> getAllEmployee() {

        return employeeRepository.findAll();
    }

    @GetMapping(value = "/employees/{id}")
    private Employee getEmployeeById(@PathVariable Long id) {

        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping("/employees")
    private Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try{
            Employee exsitEmployee=getEmployeeById(id);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<Employee>(employee,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/employees/{id}")
    private void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
