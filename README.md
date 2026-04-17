# 🖥️ Spring Boot Employee REST API

A RESTful web service built with **Spring Boot** that manages employee data, supporting HTTP GET and POST requests. Developed as part of the **HPE (Hewlett Packard Enterprise) Software Engineering Job Simulation** on [Forage](https://www.theforage.com/).

---

**Tasks completed:**
- ✅ Task 1 — Designed a RESTful Web Service proposal (Spring Boot, HPE GreenLake, Docker/Kubernetes, JWT)
- ✅ Task 2 — Built a Spring Boot REST API with GET and POST endpoints for employee management
- ✅ Task 3 — Extended and refined the REST API
- ✅ Task 4 — Wrote 21 unit tests using JUnit 5 and Mockito (100% pass rate)

---

## 🚀 Tech Stack

| Technology | Usage |
|---|---|
| Java 25 | Programming language |
| Spring Boot 3.x | REST API framework |
| Gradle | Build tool |
| JUnit 5 | Unit testing framework |
| Mockito / MockMvc | Mocking and controller testing |

---

## 📁 Project Structure

```
restservice/
├── src/
│   ├── main/java/com/example/restservice/
│   │   ├── Employee.java              # Employee model
│   │   ├── Employees.java             # Wrapper for employee list
│   │   ├── EmployeeController.java    # REST controller (GET + POST)
│   │   ├── EmployeeManager.java       # Static data store & logic
│   │   └── RestserviceApplication.java
│   └── test/java/com/example/restservice/
│       ├── EmployeeControllerTest.java   # MockMvc controller tests (9 tests)
│       ├── EmployeeManagerTest.java      # Unit tests for business logic (11 tests)
│       └── RestserviceApplicationTests.java
├── build.gradle
└── settings.gradle
```

---

## 🔌 API Endpoints

### GET `/employees`
Returns a list of all employees.

**Response:**
```json
{
  "employees": [
    {
      "employee_id": "E001",
      "first_name": "Tom",
      "last_name": "Holland",
      "email": "tom@hpe.com",
      "title": "Software Engineer"
    }
  ]
}
```

### POST `/employees`
Adds a new employee.

**Request Body:**
```json
{
  "employee_id": "E005",
  "first_name": "Jane",
  "last_name": "Doe",
  "email": "jane@hpe.com",
  "title": "Data Engineer"
}
```

**Response:** Returns the added employee object.

---

## 🧪 Unit Tests

**21 tests — 100% pass rate**

| Test Class | Tests | Coverage |
|---|---|---|
| `EmployeeControllerTest` | 9 | GET & POST endpoints, status codes, response body, content type |
| `EmployeeManagerTest` | 11 | Add/get employees, field validation, edge cases |
| `RestserviceApplicationTests` | 1 | Spring context loads |

---

## ⚙️ How to Run

### Prerequisites
- Java 25
- Gradle (or use the included `gradlew`)

### Start the server
```bash
./gradlew bootRun
```
Server runs at `http://localhost:8080`

### Run tests
```bash
./gradlew test
```

### View test report
```
build/reports/tests/test/index.html
```

---

## 🏢 About HPE Forage Simulation

> This project was built as part of the [HPE Software Engineering Job Simulation](https://www.theforage.com/simulations/hewlett-packard-enterprise/software-engineering-pcjj) on Forage. The simulation provided real-world tasks mirroring the kind of work done by software engineers at Hewlett Packard Enterprise, including API design, backend development, and test-driven development.
