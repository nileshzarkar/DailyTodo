---
description: "Use when generating or modifying Java, JavaScript, HTML, CSS, configuration, and documentation files in this repository. Enforces repository-wide coding standards for readability, consistency, and maintainability."
applyTo: "**/*"
---

<!-- Tip: Use /create-instructions in chat to generate content with agent assistance -->

## Purpose

These instructions define repository-wide coding standards that apply to all Java, JavaScript, HTML, CSS, configuration, and documentation files in this project.

The goals are to:

* Maintain consistency across the codebase.
* Improve readability and maintainability.
* Encourage simple and predictable implementations.
* Reduce technical debt.
* Ensure generated code follows established project conventions.

---

## Code Quality Principles

* Prefer clarity over cleverness.
* Write code that is easy to understand and maintain.
* Follow the Single Responsibility Principle where practical.
* Keep methods, functions, and classes focused on one responsibility.
* Prioritize readability before optimization.
* Reuse existing code before creating new implementations.
* Avoid duplicate logic whenever possible.
* Keep solutions simple and practical.
* Avoid premature optimization.
* Avoid unnecessary abstractions and overengineering.

---

## Naming Conventions

* Use descriptive and intention-revealing names.
* Prefer full words over abbreviations.
* Use consistent naming conventions throughout the project.
* Avoid generic names such as `data`, `value`, `temp`, `obj`, or `result`.
* Avoid single-letter variable names except for common loop counters (`i`, `j`).

### Java

* Classes: PascalCase
* Interfaces: PascalCase
* Methods: camelCase
* Variables: camelCase
* Constants: UPPER_SNAKE_CASE

### JavaScript

* Functions: camelCase
* Variables: camelCase
* Constants: UPPER_SNAKE_CASE
* Files: kebab-case where possible

### CSS

* Use meaningful and descriptive class names.
* Prefer kebab-case naming.
* Avoid cryptic class names.

### Preferred Examples

```text
TodoService
TodoRepository
completedTaskCount
fetchTodos()
todo-list
todo-card
```

### Avoid

```text
Svc
Repo
tmp
data
obj
x
t1
box1
```

---

## File and Folder Structure

* Keep files focused on a single responsibility.
* Keep folder structures logical and predictable.
* Avoid deeply nested folder hierarchies.
* Group related files together.
* Follow the existing project structure before creating new folders.
* Place reusable code in shared utility locations.
* Reuse existing modules whenever possible.
* Do not reorganize project structure unless explicitly requested.

### General Guidelines

* Java classes should be organized by responsibility.
* JavaScript should be split into reusable modules.
* CSS should be separated by layout, components, and application styling.
* Static assets should be grouped logically.

---

## Comments and Documentation

* Prefer self-documenting code whenever possible.
* Use comments to explain *why*, not *what*.
* Avoid redundant comments that repeat the code.
* Keep comments accurate and up to date.
* Remove obsolete comments when modifying code.
* Document public APIs and reusable components.
* Document non-obvious business rules and assumptions.
* Include examples when they improve understanding.

### Java

* Generate Javadoc for public classes, interfaces, and methods.
* Document method parameters and return values when helpful.

### JavaScript

* Use JSDoc for reusable functions and modules when appropriate.
* Document complex business logic.

### Good Comment

```java
// Retry because the external service may return temporary failures.
```

### Avoid

```java
// Increment counter
counter++;
```

---

## Error Handling

* Do not silently ignore errors.
* Handle failures explicitly and predictably.
* Validate inputs before processing.
* Fail fast when encountering invalid states.
* Return meaningful error messages.
* Log useful debugging information.
* Avoid hiding exceptions.
* Keep error handling consistent across the application.
* Avoid exposing internal implementation details to end users.

### Java

* Use appropriate exception types.
* Catch exceptions only when they can be handled meaningfully.

### JavaScript

* Use try/catch for asynchronous operations when required.
* Log errors during development and debugging.

---

## Imports and Dependencies

* Keep imports organized and minimal.
* Remove unused imports.
* Prefer existing project utilities before creating new implementations.
* Avoid introducing duplicate libraries.
* Do not add new dependencies without clear justification.
* Prefer standard language and platform features when possible.
* Follow existing dependency management practices.
* Keep dependencies up to date and consistent.

### Java

* Use standard Java and project-approved libraries first.
* Avoid unnecessary third-party frameworks.

### JavaScript

* Prefer native browser APIs before adding libraries.
* Avoid introducing frameworks unless explicitly requested.

---

## Formatting and Style

* Follow the repository's existing formatting conventions.
* Maintain consistent indentation and spacing.
* Use whitespace intentionally to improve readability.
* Keep line lengths reasonable.
* Organize code into logical sections.
* Keep methods and functions concise and focused.
* Prefer readability over compact code.
* Use automated formatting tools when available.

### Java

* Place opening braces according to existing project conventions.
* Keep methods small and focused.

### JavaScript

* Use `const` by default.
* Use `let` when reassignment is required.
* Avoid `var`.
* Prefer template literals over string concatenation.

### CSS

* Keep selectors simple and maintainable.
* Avoid excessive selector nesting.
* Reuse styles where possible.
* Keep styles modular and organized.

---

## Refactoring and Changes

* Make the smallest change necessary to solve the problem.
* Preserve existing behavior unless explicitly requested otherwise.
* Improve readability when modifying code.
* Remove dead code when it is safe to do so.
* Avoid unrelated refactoring as part of feature work.
* Reuse existing functionality whenever possible.
* Keep changes localized and easy to review.
* Update related documentation when behavior changes.
* Follow existing patterns before introducing new ones.

### Before Adding New Code

1. Check for an existing implementation.
2. Check for reusable utilities or helpers.
3. Check for established project patterns.
4. Extend existing functionality when appropriate.
5. Create new code only when necessary.

---

## Security Considerations

* Never hardcode credentials, secrets, or API keys.
* Validate and sanitize user inputs.
* Avoid exposing sensitive information in logs.
* Follow secure coding practices.
* Use environment-specific configuration for sensitive values.
* Minimize unnecessary data exposure.

---

## Copilot Behavior

When generating code:

* Follow existing project conventions and architecture.
* Generate complete and working implementations.
* Reuse existing code whenever possible.
* Preserve the current folder structure.
* Avoid speculative features.
* Avoid overengineering.
* Keep solutions maintainable and production-ready.
* Minimize unnecessary code changes.
* Ask for clarification only when requirements are ambiguous.
* Prioritize consistency with the existing codebase.
* Generate code that is easy for junior and senior developers to understand.

