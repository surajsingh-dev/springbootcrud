package com.MyProject.springboot.controller;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee Not Found !"+id);
    }
}
