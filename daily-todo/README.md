# DailyDots TODO Tracker

DailyDots is a web-based daily task tracker built with Quarkus, H2, and vanilla JavaScript.
It provides a REST API and a multi-page frontend for creating, organizing, and tracking TODO items.

## Quick Start

### Prerequisites

- Java 21

Maven is not required globally because this project includes Maven Wrapper.

### Run locally

Windows:

```cmd
mvnw.cmd quarkus:dev
```

Linux/macOS:

```bash
./mvnw quarkus:dev
```

App URLs after startup:

- Frontend dashboard: http://localhost:8080/
- OpenAPI spec: http://localhost:8080/q/openapi

## Features

- Dashboard with live summary counts:
  - Total
  - Pending
  - In Progress
  - Completed
  - Overdue
- Create TODO items with:
  - Title
  - Description
  - Priority (LOW, MEDIUM, HIGH, CRITICAL)
  - Status (PENDING, IN_PROGRESS, COMPLETED)
  - Category
  - Due date
  - Live character counters on Add TODO page:
    - Title: 50 characters max (frontend only)
    - Description: 500 characters max (frontend only)
- Filter and search TODOs by status, priority, category, text, completion visibility, and past/completed state
- Sort TODOs by priority, status, due date, or created date
- Edit TODO items
- Mark TODO as completed
- Delete TODO items
- View past and completed history

## Tech Stack

- Backend: Quarkus 3.33.1 (Jakarta REST)
- Language: Java 21
- ORM: Hibernate ORM with Panache
- Database: H2 in-memory database
- Frontend: HTML, CSS, vanilla JavaScript (ES modules)
- Build tool: Maven Wrapper

## Project Structure

```text
src/
  main/
    java/org/dailydots/
      dto/
      entity/
      repository/
      resource/
      service/
    resources/
      application.properties
      META-INF/resources/
        *.html
        css/
        js/
```

## Build and Test

Windows:

```cmd
mvnw.cmd clean package
mvnw.cmd test
```

Linux/macOS:

```bash
./mvnw clean package
./mvnw test
```

## REST API Overview

Base path: `/api/todos`

- `GET /api/todos`
  - Query params:
    - `search`
    - `status`
    - `priority`
    - `category`
    - `includeCompleted` (default `true`)
    - `showPastOnly` (default `false`)
    - `sortBy` (`priority`, `status`, `dueDate`, `createdDate`)
    - `sortDir` (`asc`, `desc`)
- `GET /api/todos/summary`
- `GET /api/todos/history`
- `GET /api/todos/{id}`
- `POST /api/todos`
- `PUT /api/todos/{id}`
- `PATCH /api/todos/{id}/status?status=COMPLETED`
- `DELETE /api/todos/{id}`

### Create/Update request payload

```json
{
  "title": "Finish project README",
  "description": "Add setup and API sections",
  "priority": "HIGH",
  "status": "IN_PROGRESS",
  "category": "work",
  "dueDate": "2026-06-08"
}
```

Note: On the Add TODO page, the frontend currently enforces `title` up to 50 characters and `description` up to 500 characters with live counters. Backend and database validation rules are unchanged.

## Environment and Configuration

This project currently requires no custom environment variables for local development.

Main runtime configuration is in `src/main/resources/application.properties`, including:

- HTTP port and CORS
- H2 datasource URL and credentials
- Hibernate schema generation and SQL logging

## Documentation

- [Architecture](docs/architecture.md)
- [Development Guide](docs/development.md)
- [Contributing Guide](docs/contributing.md)
