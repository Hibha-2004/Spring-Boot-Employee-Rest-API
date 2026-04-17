package com.example.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // -------------------------------------------------------
    // GET /employees Tests
    // -------------------------------------------------------

    @Test
    void getEmployees_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployees_returnsJsonContentType() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getEmployees_responseContainsEmployeesList() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(jsonPath("$.employees").isArray());
    }

    @Test
    void getEmployees_listIsNotEmpty() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(jsonPath("$.employees").isNotEmpty());
    }

    @Test
    void getEmployees_firstEmployeeHasRequiredFields() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(jsonPath("$.employees[0].employee_id").exists())
                .andExpect(jsonPath("$.employees[0].first_name").exists())
                .andExpect(jsonPath("$.employees[0].last_name").exists())
                .andExpect(jsonPath("$.employees[0].email").exists())
                .andExpect(jsonPath("$.employees[0].title").exists());
    }

    // -------------------------------------------------------
    // POST /employees Tests
    // -------------------------------------------------------

    @Test
    void addEmployee_returnsOkStatus() throws Exception {
        Employee newEmployee = new Employee("E099", "Test", "User", "test@hpe.com", "QA Engineer");

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployee_returnsAddedEmployeeInResponse() throws Exception {
        Employee newEmployee = new Employee("E100", "Jane", "Doe", "jane@hpe.com", "Data Engineer");

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(jsonPath("$.employee_id").value("E100"))
                .andExpect(jsonPath("$.first_name").value("Jane"))
                .andExpect(jsonPath("$.last_name").value("Doe"))
                .andExpect(jsonPath("$.email").value("jane@hpe.com"))
                .andExpect(jsonPath("$.title").value("Data Engineer"));
    }

    @Test
    void addEmployee_responseContentTypeIsJson() throws Exception {
        Employee newEmployee = new Employee("E101", "Alice", "Smith", "alice@hpe.com", "PM");

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void addEmployee_withEmptyBody_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addEmployee_withPartialFields_returnsOk() throws Exception {
        // Employee with only some fields set (nulls for missing ones)
        Employee partial = new Employee();
        partial.setEmployee_id("E102");
        partial.setFirst_name("Bob");

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partial)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee_id").value("E102"))
                .andExpect(jsonPath("$.first_name").value("Bob"));
    }
}
