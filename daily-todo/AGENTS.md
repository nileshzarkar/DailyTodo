# AGENTS.md

## Project Context

This repository contains a Daily TODO Tracker web application.

### Technology Stack

* Backend: Quarkus 3.33.1
* Language: Java 21
* Database: H2 In-Memory Database
* ORM: Hibernate ORM with Panache
* Frontend: HTML, CSS, Vanilla JavaScript
* API Style: REST APIs
* Build Tool: Maven Wrapper
* Configuration: application.properties

The application allows users to:

* Create TODO items
* View TODO items
* Mark TODO items as completed
* Delete TODO items
* Manage daily tasks

---

## Instruction Files

* .github/instructions/general.instructions.md: Repository-wide coding standards for Java, JavaScript, HTML, CSS, config, and docs.
* .github/instructions/javascript.instructions.md: JavaScript-specific standards for ES6+, modular structure, API usage, and error handling.
* .github/instructions/css.instructions.md: CSS-specific standards for naming, selectors, layout, variables, and responsive styling.
* .github/instructions/design.instructions.md: UI/UX standards for layout consistency, accessibility, spacing, and interaction design.

---

## Architecture

Follow the layered architecture below:

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

Business logic belongs in services.

Repositories are responsible for database access only.

---

## Project Structure

Backend structure:

```text
src/main/java/

resource/
service/
repository/
entity/
dto/
```

Frontend structure:

```text
src/main/resources/META-INF/resources/

index.html

css/
├── base.css
├── layout.css
├── components.css
└── style.css

js/
├── api.js
├── todo-service.js
├── ui.js
├── utils.js
└── app.js
```

Always follow the existing structure before creating new folders.

---

## Quarkus Rules

* Use Quarkus 3.33.1 compatible code.
* Use Jakarta namespaces (`jakarta.*`).
* Use Quarkus REST APIs.
* Use Hibernate ORM with Panache.
* Use H2 for development.
* Use application.properties for configuration.
* Generate code compatible with Java 21.
* Keep dependencies minimal.

Do not introduce:

* Spring Boot
* Lombok
* Gradle
* Reactive frameworks unless explicitly requested

---

## Maven And Build Rules

Assume Maven is NOT installed globally.

Always use Maven Wrapper commands.

If wrapper files are missing:

```bash
mvn wrapper:wrapper
```

Preferred commands:

Build:

```bash
mvnw.cmd clean package
```

Run:

```bash
mvnw.cmd quarkus:dev
```

Test:

```bash
mvnw.cmd test
```

Do not generate documentation that depends on globally installed Maven.

---

## REST API Rules

Use RESTful endpoints.

Preferred pattern:

```http
GET    /api/todos
GET    /api/todos/{id}
POST   /api/todos
PUT    /api/todos/{id}
DELETE /api/todos/{id}
```

Use appropriate HTTP methods.

Return meaningful HTTP status codes.

Avoid action-based endpoints such as:

```http
/api/createTodo
/api/deleteTodo
```

unless explicitly requested.

---

## Database Rules

Use H2 In-Memory Database.

Keep schema simple.

Default TODO entity:

```text
id
title
completed
createdDate
```

Avoid unnecessary relationships and complexity.

Do not introduce additional fields unless requested.

---

## JavaScript Rules

* Use modern ES6+ syntax.
* Prefer const over let.
* Do not use var.
* Use Fetch API for backend communication.
* Use async/await for asynchronous operations.
* Separate API logic, business logic, and UI logic.
* Keep DOM manipulation inside UI modules.
* Keep functions small and single-purpose.
* Reuse existing modules whenever possible.

Preferred:

```javascript
const response = await fetch('/api/todos');
```

Avoid:

```javascript
var xhr = new XMLHttpRequest();
```

---

## CSS Rules

* Use descriptive kebab-case class names.
* Keep selectors simple.
* Prefer class selectors.
* Use Flexbox and Grid for layouts.
* Use CSS variables for reusable colors and spacing.
* Avoid deep selector nesting.
* Reuse existing styles before creating new ones.
* Keep CSS responsive and maintainable.

---

## Design And UX Rules

* Prioritize usability over visual complexity.
* Use a clean and professional layout.
* Keep forms simple and easy to understand.
* Make task creation and completion obvious.
* Maintain consistent spacing and typography.
* Design for mobile and desktop screens.
* Use semantic HTML whenever possible.
* Ensure accessibility through labels and keyboard support.

Avoid:

* Unnecessary animations
* Complex dashboards
* Excessive visual effects

---

## Error Handling

* Do not silently ignore errors.
* Validate inputs before processing.
* Log useful debugging information.
* Return meaningful error messages.
* Keep error handling simple and predictable.
* Avoid exposing implementation details to users.

---

## Documentation

* Generate Javadocs for public classes and methods.
* Prefer self-documenting code.
* Use comments to explain why, not what.
* Keep comments concise and accurate.

---

## Agent Behavior

When generating code:

* Follow the existing architecture.
* Follow the existing folder structure.
* Reuse existing code whenever possible.
* Generate complete working implementations.
* Keep solutions simple and maintainable.
* Avoid speculative features.
* Avoid overengineering.
* Preserve existing behavior unless explicitly requested otherwise.
* Minimize unrelated changes.
* Ask for clarification only when requirements are ambiguous.
* Generate production-ready code compatible with Quarkus 3.33.1.
