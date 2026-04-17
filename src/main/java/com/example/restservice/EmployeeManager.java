package com.example.restservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeManager {

    private static List<Employee> employeeList = new ArrayList<>(Arrays.asList(
        new Employee("E001", "Hibha", "Mehrok", "hibha@hpe.com", "Software Engineer"),
        new Employee("E002", "Arjun", "Sharma", "arjun@hpe.com", "Backend Developer"),
        new Employee("E003", "Priya", "Verma", "priya@hpe.com", "Frontend Developer"),
        new Employee("E004", "Rohan", "Singh", "rohan@hpe.com", "DevOps Engineer")
    ));

    public static Employees getEmployees() {
        return new Employees(employeeList);
    }

    public static void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
