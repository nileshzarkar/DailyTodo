# Architecture

## Overview

DailyDots TODO Tracker follows a layered backend architecture and a modular frontend structure.

Backend flow:

```text
REST Resource -> Service -> Repository -> H2 Database
```

Frontend flow:

```text
Page (HTML) -> app.js -> todo-service.js -> api.js -> REST API
```

## Backend Layers

### Resource layer

- Package: `org.dailydots.resource`
- Entry point: `TodoResource`
- Responsibilities:
  - Expose REST endpoints under `/api/todos`
  - Parse query and path parameters
  - Delegate business logic to the service layer

### Service layer

- Package: `org.dailydots.service`
- Entry point: `TodoService`
- Responsibilities:
  - Validation (for example, title required)
  - Filtering, searching, and sorting behavior
  - Status transitions and completion timestamp handling
  - Summary and history projection

### Repository layer

- Package: `org.dailydots.repository`
- Entry point: `TodoRepository`
- Responsibilities:
  - Persistence operations via Panache
  - Sort resolution for supported sortable fields

### Entity and DTOs

- Entity: `Todo`
  - Core fields: `id`, `title`, `description`, `priority`, `status`, `category`, `dueDate`, `createdDate`, `updatedDate`, `completedDate`
- Enums:
  - `TodoStatus`: `PENDING`, `IN_PROGRESS`, `COMPLETED`
  - `TodoPriority`: `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`
- DTOs:
  - `TodoRequest`
  - `TodoResponse`
  - `TodoSummaryResponse`

## Frontend Modules

Static web resources are served from `src/main/resources/META-INF/resources`.

Pages:

- `/` (Dashboard)
- `/add-todo.html`
- `/todos.html`
- `/edit-todo.html`
- `/history.html`

JavaScript modules:

- `js/app.js`: page bootstrap and event wiring
- `js/todo-service.js`: app-level TODO operations
- `js/api.js`: Fetch API calls
- `js/ui.js`: rendering and form mapping
- `js/utils.js`: shared helpers (feedback, formatting, query string)

## Data and Persistence

- Database: H2 in-memory
- JPA/Hibernate ORM with Panache
- Schema generation strategy: `drop-and-create` (from `application.properties`)

This setup is convenient for local development and resets data on restart.

## Runtime Configuration

Current key settings in `application.properties`:

- `quarkus.http.port=8080`
- CORS enabled
- In-memory H2 datasource
- Hibernate SQL logging enabled
