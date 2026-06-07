# Development Guide

## Prerequisites

- Java 21

Maven Wrapper is included, so global Maven installation is not required.

## Start the app

Windows:

```cmd
mvnw.cmd quarkus:dev
```

Linux/macOS:

```bash
./mvnw quarkus:dev
```

The application runs on http://localhost:8080 by default.

## Build, test, and clean

Windows:

```cmd
mvnw.cmd clean
mvnw.cmd test
mvnw.cmd clean package
```

Linux/macOS:

```bash
./mvnw clean
./mvnw test
./mvnw clean package
```

## Useful endpoints during development

- Dashboard UI: http://localhost:8080/
- OpenAPI document: http://localhost:8080/q/openapi

## Frontend pages

- Dashboard: `/`
- Add TODO: `/add-todo.html`
- View TODOs: `/todos.html`
- Edit TODO: `/edit-todo.html?id={todoId}`
- Past and completed: `/history.html`

## API quick reference

Base path: `/api/todos`

- `GET /api/todos`
- `GET /api/todos/summary`
- `GET /api/todos/history`
- `GET /api/todos/{id}`
- `POST /api/todos`
- `PUT /api/todos/{id}`
- `PATCH /api/todos/{id}/status?status={PENDING|IN_PROGRESS|COMPLETED}`
- `DELETE /api/todos/{id}`

### Example create request

```http
POST /api/todos
Content-Type: application/json
```

```json
{
  "title": "Prepare weekly plan",
  "description": "List top priorities for the week",
  "priority": "MEDIUM",
  "status": "PENDING",
  "category": "planning",
  "dueDate": "2026-06-09"
}
```

## Configuration

Application configuration is in `src/main/resources/application.properties`.

Key values currently configured:

- HTTP port: `8080`
- H2 JDBC URL: in-memory datasource
- H2 username/password: `sa` / `sa`
- Hibernate schema mode: `drop-and-create`

No additional environment variables are required for local development at this time.
