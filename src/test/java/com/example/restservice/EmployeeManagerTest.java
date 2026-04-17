package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagerTest {

    // Reset the static employeeList before each test to ensure isolation
    @BeforeEach
    void resetEmployeeList() throws Exception {
        Field field = EmployeeManager.class.getDeclaredField("employeeList");
        field.setAccessible(true);
        List<Employee> freshList = new ArrayList<>();
        freshList.add(new Employee("E001", "Hibha", "Mehrok", "hibha@hpe.com", "Software Engineer"));
        freshList.add(new Employee("E002", "Arjun", "Sharma", "arjun@hpe.com", "Backend Developer"));
        freshList.add(new Employee("E003", "Priya", "Verma", "priya@hpe.com", "Frontend Developer"));
        freshList.add(new Employee("E004", "Rohan", "Singh", "rohan@hpe.com", "DevOps Engineer"));
        field.set(null, freshList);
    }

    // -------------------------------------------------------
    // getEmployees() Tests
    // -------------------------------------------------------

    @Test
    void getEmployees_returnsNonNullEmployeesObject() {
        Employees result = EmployeeManager.getEmployees();
        assertNotNull(result);
    }

    @Test
    void getEmployees_returnsNonEmptyList() {
        Employees result = EmployeeManager.getEmployees();
        assertFalse(result.getEmployees().isEmpty());
    }

    @Test
    void getEmployees_returnsCorrectInitialCount() {
        Employees result = EmployeeManager.getEmployees();
        assertEquals(4, result.getEmployees().size());
    }

    @Test
    void getEmployees_firstEmployeeIdIsE001() {
        Employees result = EmployeeManager.getEmployees();
        assertEquals("E001", result.getEmployees().get(0).getEmployee_id());
    }

    @Test
    void getEmployees_firstEmployeeNameIsHibha() {
        Employees result = EmployeeManager.getEmployees();
        assertEquals("Hibha", result.getEmployees().get(0).getFirst_name());
    }

    // -------------------------------------------------------
    // addEmployee() Tests
    // -------------------------------------------------------

    @Test
    void addEmployee_increasesListSizeByOne() {
        int sizeBefore = EmployeeManager.getEmployees().getEmployees().size();
        EmployeeManager.addEmployee(new Employee("E005", "New", "User", "new@hpe.com", "Tester"));
        int sizeAfter = EmployeeManager.getEmployees().getEmployees().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    void addEmployee_newEmployeeIsRetrievable() {
        Employee newEmp = new Employee("E010", "Test", "Engineer", "test@hpe.com", "SDE");
        EmployeeManager.addEmployee(newEmp);

        List<Employee> list = EmployeeManager.getEmployees().getEmployees();
        boolean found = list.stream()
                .anyMatch(e -> "E010".equals(e.getEmployee_id()));
        assertTrue(found);
    }

    @Test
    void addEmployee_correctlyStoresAllFields() {
        Employee newEmp = new Employee("E011", "Zara", "Khan", "zara@hpe.com", "ML Engineer");
        EmployeeManager.addEmployee(newEmp);

        List<Employee> list = EmployeeManager.getEmployees().getEmployees();
        Employee stored = list.stream()
                .filter(e -> "E011".equals(e.getEmployee_id()))
                .findFirst()
                .orElse(null);

        assertNotNull(stored);
        assertEquals("Zara", stored.getFirst_name());
        assertEquals("Khan", stored.getLast_name());
        assertEquals("zara@hpe.com", stored.getEmail());
        assertEquals("ML Engineer", stored.getTitle());
    }

    @Test
    void addMultipleEmployees_allArePresent() {
        EmployeeManager.addEmployee(new Employee("E020", "A", "B", "a@b.com", "Role1"));
        EmployeeManager.addEmployee(new Employee("E021", "C", "D", "c@d.com", "Role2"));

        List<Employee> list = EmployeeManager.getEmployees().getEmployees();
        long count = list.stream()
                .filter(e -> e.getEmployee_id().equals("E020") || e.getEmployee_id().equals("E021"))
                .count();
        assertEquals(2, count);
    }

    @Test
    void addEmployee_withNullFields_doesNotThrow() {
        Employee nullFieldsEmp = new Employee(null, null, null, null, null);
        assertDoesNotThrow(() -> EmployeeManager.addEmployee(nullFieldsEmp));
    }
}
