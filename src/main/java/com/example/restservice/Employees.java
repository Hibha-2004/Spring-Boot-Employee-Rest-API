package com.example.restservice;

import java.util.List;

public class Employees {

    private List<Employee> employees;

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}
