# Copilot Instructions

## Project Overview

This project is a Daily TODO Tracker web application.

### Technology Stack

* Backend: Quarkus
* Database: H2 In-Memory Database
* Frontend: HTML, CSS, JavaScript
* API Style: RESTful APIs
* Build Tool: Maven

The application allows users to create, update, complete, delete, and view daily TODO items.

---

## General Coding Guidelines

* Write clean, readable, and maintainable code.
* Prefer simplicity over clever implementations.
* Follow consistent formatting.
* Avoid unnecessary abstractions.
* Generate production-quality code.
* Keep implementations beginner-friendly and easy to understand.

---

## Naming Conventions

Use descriptive names.

### Good Examples

```java
TodoService
TodoRepository
TodoResource
TodoDto
completedTaskCount
```

### Avoid

```java
TodoSvc
TodoRepo
cnt
tmp
data
```

---

## Quarkus Backend Structure

Always organize backend code using the following layered architecture:

```text
src/main/java/

resource/
service/
repository/
entity/
dto/
```

Example:

```text
resource/
 └── TodoResource.java

service/
 └── TodoService.java

repository/
 └── TodoRepository.java

entity/
 └── Todo.java

dto/
 └── TodoDto.java
```

---

## Build and Maven Guidelines

### Maven Wrapper Requirement

Assume that Maven is NOT installed locally on the developer machine.

If Maven Wrapper files are missing, generate them using:

```bash
mvn wrapper:wrapper
```

After Maven Wrapper is available, always prefer Maven Wrapper commands instead of Maven commands.

### Preferred Commands

Build Project

```bash
./mvnw clean package
```

Windows:

```cmd
mvnw.cmd clean package
```

Run Application

```bash
./mvnw quarkus:dev
```

Windows:

```cmd
mvnw.cmd quarkus:dev
```

Run Tests

```bash
./mvnw test
```

Windows:

```cmd
mvnw.cmd test
```

Clean Build

```bash
./mvnw clean
```

Windows:

```cmd
mvnw.cmd clean
```

### Copilot Rules

* Prefer Maven Wrapper (`mvnw`) over Maven (`mvn`).
* When generating documentation, README files, setup guides, or build instructions, use Maven Wrapper commands.
* Assume Java is installed.
* Do not assume Maven is installed globally.
* If wrapper files are missing, suggest generating them using:

```bash
mvn wrapper:wrapper
```

* For Windows examples, prefer:

```cmd
mvnw.cmd
```

* For Linux/macOS examples, prefer:

```bash
./mvnw
```

---

## Backend Flow

Use the following flow:

```text
REST Resource
    ↓
Service
    ↓
Repository
    ↓
H2 Database
```

Resources must not directly access repositories.

---

## REST API Standards

Use RESTful endpoint naming.

```http
GET    /api/todos
GET    /api/todos/{id}
POST   /api/todos
PUT    /api/todos/{id}
DELETE /api/todos/{id}
```

Use proper HTTP methods.

Return meaningful HTTP status codes.

---

## TODO Entity

Use a simple TODO model.

```java
id
title
completed
createdDate
```

Example:

```java
public class Todo {

    private Long id;

    private String title;

    private boolean completed;

    private LocalDate createdDate;
}
```

Do not add extra fields unless explicitly requested.

---

## Frontend Structure

Organize JavaScript code into modules.

```text
js/
├── api.js
├── todo-service.js
├── ui.js
├── utils.js
└── app.js
```

Responsibilities:

### api.js

Contains all fetch calls.

### todo-service.js

Contains TODO business operations.

### ui.js

Contains DOM manipulation logic.

### utils.js

Contains reusable helper functions.

### app.js

Application entry point.

---

## CSS Structure

Use modular CSS.

```text
css/
├── base.css
├── layout.css
├── components.css
└── style.css
```

Responsibilities:

### base.css

Typography, reset styles, common variables.

### layout.css

Page layouts and containers.

### components.css

Buttons, cards, forms, tables.

### style.css

Application-specific styling.

---

## UI Design Guidelines

Keep the UI simple and clean.

Preferred characteristics:

* White background
* Clean typography
* Simple cards
* Simple forms
* Simple buttons
* Responsive layout
* Mobile-friendly design

Avoid:

* Overly complex animations
* Fancy effects
* Unnecessary dashboards
* Heavy visual frameworks

Focus on usability.

---

## JavaScript Standards

Use modern JavaScript.

Prefer:

```javascript
const
let
async/await
fetch
template literals
```

Avoid:

```javascript
var
XMLHttpRequest
```

Use ES6+ syntax.

---

## API Communication

Use Fetch API exclusively.

Example:

```javascript
const response = await fetch('/api/todos');
const data = await response.json();
```

Do not introduce Axios unless explicitly requested.

---

## Error Handling

Keep error handling simple.

Example:

```javascript
try {
    // operation
} catch (error) {
    console.error(error);
}
```

Backend should log errors appropriately.

Do not create custom exception frameworks unless requested.

---

## Documentation Requirements

Generate Javadoc for:

* Classes
* Interfaces
* Public methods

Example:

```java
/**
 * Service responsible for managing TODO items.
 */
public class TodoService {
}
```

---

## Database Guidelines

Use H2 In-Memory Database.

Prefer simple schemas.

Avoid unnecessary relationships.

Keep data model lightweight.

---

## Code Generation Preferences

When generating code:

* Generate complete files.
* Include imports.
* Include package declarations.
* Include Javadocs.
* Keep code simple.
* Avoid speculative features.
* Avoid overengineering.
* Avoid adding frameworks not already present in the project.

---

## Testing Preferences

Do not generate test classes unless explicitly requested.

Focus on application code first.

---

## Copilot Behaviour

When implementing new features:

1. Follow the defined folder structure.
2. Reuse existing modules whenever possible.
3. Keep APIs RESTful.
4. Keep UI simple and clean.
5. Use descriptive naming.
6. Add Javadocs to public classes and methods.
7. Prefer maintainability over complexity.
8. Generate complete working solutions rather than partial snippets.
9. Preserve existing project structure and coding style.
10. Ask for clarification only when requirements are ambiguous.

---

## Quarkus Preferences

- Use Quarkus REST (Jakarta REST) Quarkus 3.33.1.
- Use Hibernate ORM with Panache.
- Use H2 In-Memory Database for development.
- Use application.properties for configuration.
- Prefer constructor injection where possible.
- Keep dependencies minimal.
- Do not introduce Spring Boot dependencies.
- Do not introduce Lombok unless explicitly requested.

```
```
