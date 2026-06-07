# Contributing Guide

## Workflow

1. Create a feature branch from your main working branch.
2. Make focused changes for one concern at a time.
3. Verify build and tests locally.
4. Open a pull request with a clear summary.

## Local quality checks

Windows:

```cmd
mvnw.cmd test
mvnw.cmd clean package
```

Linux/macOS:

```bash
./mvnw test
./mvnw clean package
```

## Coding expectations

- Keep changes small and easy to review.
- Follow existing architecture:
  - Resource -> Service -> Repository
- Keep business rules in the service layer.
- Keep repository logic focused on persistence.
- Reuse existing frontend modules before introducing new ones.
- Use descriptive names and maintain readability.

## REST and data model consistency

- Keep endpoint style RESTful under `/api/todos`.
- Reuse existing enum values for status and priority.
- Avoid adding new TODO fields unless there is a clear requirement.

## Documentation updates

When behavior, commands, API, or structure changes:

- Update `README.md`.
- Update related docs under `docs/`.
- Ensure command examples remain copy-paste ready.
