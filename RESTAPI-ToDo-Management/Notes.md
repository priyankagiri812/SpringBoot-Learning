# ❗ Spring Boot Error: not-null property references a null value

## 🔴 Error
---

## 🧠 Root Cause

Missing `@RequestBody` annotation in Controller:

```java
@PostMapping
public ToDoDTO create(ToDoDTO dto) {   // ❌ Missing @RequestBody

What happens:
JSON from Postman is NOT mapped to DTO
DTO fields become null
Entity receives null values
Database throws NOT NULL constraint error

🛠️ Possible Solutions
✅ 1. Add @RequestBody (Main Fix)
Required for REST APIs
Maps JSON → Java Object

✅ 2. Add Validation (Best Practice)
@Valid @RequestBody ToDoDTO dto

✅ 4. Use Manual Mapping (Safer)
todo.setDescription(dto.getDescription());

✅ 5. Ensure DTO Matches Entity
Field names must match exactly
description → description ✅
Avoid typos ❌