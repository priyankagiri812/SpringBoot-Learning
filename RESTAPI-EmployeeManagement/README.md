# 🏢 Employee Management System (Spring Boot)

## 📌 Project Overview

This project demonstrates a **Spring Boot REST API** for managing Employees and Departments using **One-to-Many / Many-to-One relationship**.

* One Department can have multiple Employees
* Each Employee belongs to one Department

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA (Hibernate)
* MySQL
* Lombok
* ModelMapper
* Maven

---

## 🧠 Key Concepts Covered

* REST API Development
* DTO Pattern
* Entity Relationships (One-to-Many, Many-to-One)
* Exception Handling
* Layered Architecture (Controller → Service → Repository)

---

## 📂 Project Structure

```
controller/     → REST APIs
service/        → Business logic
repository/     → Database access (JPA)
entity/         → JPA Entities
dto/            → Data Transfer Objects
exception/      → Global Exception Handling
```

---

## 🔗 Entity Relationship

### Department Entity

```java
@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Employee> employees;
```

### Employee Entity

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id", nullable = false)
private Department department;
```

### 🧠 Explanation

* One Department → Many Employees
* Employee table contains `department_id` (foreign key)
* `mappedBy` defines inverse side
* `cascade` propagates operations
* `orphanRemoval` deletes removed employees

---

## 🚀 API Endpoints

### 📌 Department APIs

| Method | Endpoint                | Description          |
| ------ | ----------------------- | -------------------- |
| POST   | `/api/departments`      | Create department    |
| GET    | `/api/departments/{id}` | Get department by ID |
| GET    | `/api/departments`      | Get all departments  |
| PUT    | `/api/departments/{id}` | Update department    |

---

### 📌 Employee APIs

| Method | Endpoint                             | Description                 |
| ------ | ------------------------------------ | --------------------------- |
| POST   | `/api/{departmentID}/employees`      | Create employee             |
| GET    | `/api/{departmentID}/employees/{id}` | Get employee by ID          |
| GET    | `/api/employees`                     | Get all employees           |
| GET    | `/api/{departmentID}/employees`      | Get employees by department |

---

## 🔄 Flow of Application

1. Client sends request (Postman/UI)
2. Controller receives request
3. Service processes business logic
4. Repository interacts with DB
5. Entity is saved/fetched
6. Response is mapped to DTO
7. Response returned to client

---

## 🔁 DTO Mapping Example

```java
DepartmentDTO dto = modelMapper.map(department, DepartmentDTO.class);
```

---

## ⚠️ Common Issues Faced

### ❌ JSON parse error (null to long)

✔ Solution: Use `Long` instead of `long`

---

### ❌ PropertyValueException

✔ Cause: Null value for non-null column
✔ Fix: Ensure required fields are passed

---

### ❌ Infinite Recursion

✔ Use DTO instead of returning entity directly

---

## 🎯 Learning Outcomes

* Understood JPA relationships
* Implemented DTO pattern
* Learned clean API design
* Practiced logging and exception handling

---

## 💡 Future Improvements

* Add validation (`@Valid`)
* Implement pagination
* Add Swagger documentation
* Add unit & integration tests

---

## 🙌 Author

**Priyanka Giri**

