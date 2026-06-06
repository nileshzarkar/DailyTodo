````md
---
applyTo: "**/*.js"
---

# JavaScript Coding Standards

## Purpose

These instructions define JavaScript coding standards for the project.

The goals are to:

- Maintain consistent coding practices.
- Improve readability and maintainability.
- Encourage modular and reusable code.
- Reduce bugs through predictable patterns.
- Generate clean, production-ready JavaScript.

---

## General Principles

- Prefer simplicity over complexity.
- Write code that is easy to understand and maintain.
- Keep functions focused on a single responsibility.
- Reuse existing functionality before creating new implementations.
- Avoid duplicate logic.
- Follow established project patterns.
- Prioritize readability over clever solutions.
- Avoid unnecessary abstractions and overengineering.

---

## Naming Conventions

- Use descriptive and intention-revealing names.
- Prefer full words over abbreviations.
- Use camelCase for variables and functions.
- Use UPPER_SNAKE_CASE for constants.
- Use kebab-case for file names when appropriate.
- Avoid generic names such as `data`, `temp`, `obj`, or `value`.

### Preferred Examples

```javascript
fetchTodos()
createTodo()
completedTaskCount
todoList
API_BASE_URL
````

### Avoid

```javascript
get()
tmp
data
obj
x
cnt
```

---

## File Organization

* Keep files focused on a single responsibility.
* Separate API logic from UI logic.
* Separate business logic from DOM manipulation.
* Group related functionality together.
* Reuse existing modules whenever possible.
* Avoid creating large monolithic files.

### Example Structure

```text
js/
├── api.js
├── todo-service.js
├── ui.js
├── utils.js
└── app.js
```

---

## Variables and Constants

* Use `const` by default.
* Use `let` only when reassignment is required.
* Do not use `var`.
* Keep variable scope as small as possible.
* Initialize variables close to where they are used.

### Preferred

```javascript
const todos = [];
let currentPage = 1;
```

### Avoid

```javascript
var todos = [];
```

---

## Functions

* Keep functions small and focused.
* Functions should perform a single task.
* Use descriptive function names.
* Avoid deeply nested logic.
* Return early when possible to improve readability.

### Preferred

```javascript
function findTodoById(id) {
    return todos.find(todo => todo.id === id);
}
```

### Avoid

```javascript
function process(data) {
}
```

---

## Modern JavaScript

Use modern JavaScript features whenever appropriate.

### Prefer

```javascript
const
let
async/await
fetch
template literals
arrow functions
destructuring
optional chaining
```

### Example

```javascript
const todoTitle = todo?.title;

const message = `Task: ${todoTitle}`;
```

### Avoid

```javascript
var
XMLHttpRequest
string concatenation where template literals improve readability
```

---

## API Communication

* Use Fetch API for HTTP requests.
* Use async/await instead of promise chains when practical.
* Keep API calls isolated in dedicated modules.
* Handle errors appropriately.
* Reuse API helper functions.

### Preferred

```javascript
async function getTodos() {
    const response = await fetch('/api/todos');
    return response.json();
}
```

---

## DOM Manipulation

* Keep DOM manipulation isolated in UI-related modules.
* Avoid duplicating selectors.
* Cache frequently used DOM elements.
* Use meaningful element identifiers.
* Minimize direct DOM updates when possible.

### Preferred

```javascript
const todoListElement = document.getElementById('todo-list');
```

### Avoid

```javascript
document.getElementById('todo-list');
document.getElementById('todo-list');
document.getElementById('todo-list');
```

repeated multiple times.

---

## Error Handling

* Do not silently ignore errors.
* Use try/catch where appropriate.
* Log useful debugging information.
* Return meaningful error messages.
* Validate data before processing.

### Preferred

```javascript
try {
    const todos = await getTodos();
} catch (error) {
    console.error(error);
}
```

### Avoid

```javascript
try {
    await getTodos();
} catch (error) {
}
```

---

## Comments and Documentation

* Prefer self-documenting code.
* Use comments to explain why, not what.
* Avoid redundant comments.
* Keep comments up to date.
* Document reusable functions when necessary.

### Good Comment

```javascript
// Retry because the backend may temporarily be unavailable.
```

### Avoid

```javascript
// Increment counter
counter++;
```

---

## Imports and Dependencies

* Keep imports organized and minimal.
* Remove unused imports.
* Prefer native browser APIs before adding libraries.
* Reuse existing utilities before creating new ones.
* Do not introduce new dependencies without clear justification.
* Follow existing project dependency patterns.

---

## Formatting and Style

* Use consistent indentation.
* Keep line lengths reasonable.
* Organize code into logical sections.
* Use whitespace intentionally.
* Prefer readability over compact code.
* Follow project formatting conventions.

### Preferred

```javascript
if (todo.completed) {
    return;
}
```

### Avoid

```javascript
if(todo.completed){return;}
```

---

## Security Considerations

* Never hardcode secrets, credentials, or API keys.
* Validate user input before processing.
* Treat all external input as untrusted.
* Avoid exposing sensitive data in logs.
* Sanitize content before rendering when appropriate.

---

## Refactoring and Changes

* Make the smallest change necessary.
* Preserve existing behavior unless explicitly requested otherwise.
* Improve readability when modifying code.
* Reuse existing functionality whenever possible.
* Avoid unrelated refactoring.
* Follow established project patterns.

### Before Adding New Code

1. Check for an existing implementation.
2. Check for reusable helper functions.
3. Check for existing modules.
4. Extend existing functionality when appropriate.
5. Create new code only when necessary.

---

## Copilot Behavior

When generating JavaScript:

* Use modern ES6+ syntax.
* Prefer `const` and `let`.
* Use Fetch API for HTTP communication.
* Use async/await for asynchronous operations.
* Generate modular and reusable code.
* Reuse existing functions whenever possible.
* Avoid unnecessary libraries and frameworks.
* Keep code simple, maintainable, and production-ready.
* Follow the existing project structure and coding style.

```
```
