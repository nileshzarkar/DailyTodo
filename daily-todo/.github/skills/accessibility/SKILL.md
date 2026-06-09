---
name: accessibility
description: Review and improve java code and UI in the daily-todo Quarkus TODO application (HTML, CSS, JavaScript, REST) with WCAG 2.1 AA guidance.
tools: ["read", "search", "edit"]
---

# Accessibility Skill For daily-todo

## Goal

Ensure every daily-todo page is accessible by default:

- Keyboard accessible
- Screen-reader friendly
- Semantic HTML first
- Reliable focus behavior
- Accessible forms and validation
- Accessible JavaScript-driven updates
- WCAG 2.1 AA aligned

Keep fixes small and safe. Prefer native HTML and minimal CSS/JS updates over rewrites.

---

## Project Context

Application stack:

- Quarkus backend (REST)
- HTML pages under src/main/resources/META-INF/resources
- Vanilla JavaScript modules under src/main/resources/META-INF/resources/js
- CSS modules under src/main/resources/META-INF/resources/css

Primary pages in this project:

- Dashboard: index.html
- Add form: add-todo.html
- Edit form: edit-todo.html
- List and filters: todos.html
- History table: history.html

Key JavaScript modules:

- app.js: page initialization and event wiring
- ui.js: table rendering and form mapping
- api.js: REST fetch layer
- utils.js: feedback and helpers

Backend entry points to include in a11y reviews:

- org.dailydots.resource.TodoResource
- org.dailydots.service.TodoService

---

## Java Coding Standards (Quarkus)

When this skill proposes backend changes, follow these Java coding rules:

- Use Java 21 compatible code and Jakarta namespaces (jakarta.*)
- Keep layered architecture strict: Resource -> Service -> Repository
- Keep resources thin; business logic belongs in services
- Keep repositories focused on persistence concerns only
- Use constructor injection for dependencies
- Use descriptive names (avoid abbreviations and generic variable names)
- Keep methods focused and short; prefer clarity over cleverness
- Add Javadoc to public classes and public methods
- Validate request input explicitly and return meaningful messages
- Do not swallow exceptions; surface actionable errors
- Avoid introducing non-required frameworks (no Spring Boot, no Lombok)

Code style and structure guidelines:

- Use consistent package naming under org.dailydots
- Keep DTOs simple and purpose-specific (request vs response)
- Reuse existing enums and shared models where possible
- Keep null handling explicit and predictable
- Preserve existing behavior unless change is intentional and documented

---

## Microservice Standards (REST + Accessibility-Friendly Contracts)

For API design and service behavior, follow these microservice standards:

- Keep endpoints RESTful and resource-oriented
- Use proper HTTP methods and status codes
- Return stable JSON structures for both success and error responses
- Prefer field-level validation payloads for form mapping when possible:
	- message
	- fieldErrors[] with field and message
- Keep service boundaries clear and avoid leaking persistence details to clients
- Ensure idempotent behavior where appropriate (for example, safe updates)
- Keep sorting/filtering parameters explicit and validated
- Use consistent response semantics across endpoints
- Keep backward compatibility when evolving payloads

Operational and maintainability expectations:

- Keep error messages understandable for both developers and UI users
- Ensure frontend can map backend errors to accessible UI feedback
- Keep API behavior deterministic for UI announcement patterns
- Prefer small, incremental changes with minimal side effects
- Maintain testability for resource and service layers

---

## Examples Reference

Use these concrete examples when applying this skill:

Java layer examples:

- .github/skills/accessibility/examples/TodoValidationErrorResponseExample.java
- .github/skills/accessibility/examples/TodoRepositoryExample.java
- .github/skills/accessibility/examples/TodoServiceExample.java
- .github/skills/accessibility/examples/TodoResourceExample.java

Frontend accessibility examples:

- .github/skills/accessibility/examples/AccessibleTodoFormExample.html
- .github/skills/accessibility/examples/AccessibleTodosTableExample.html
- .github/skills/accessibility/examples/accessible-form-example.js
- .github/skills/accessibility/examples/accessible-api-validation-example.js
- .github/skills/accessibility/examples/accessible-components-example.css

Checklist reference:

- .github/skills/accessibility/CHECKLIST.md

---

## When To Use This Skill

Use this skill when reviewing or modifying:

- Forms (create/edit TODO)
- Tables (dashboard/list/history)
- Filter controls and action buttons
- Feedback and validation flows
- Keyboard navigation behavior
- Screen-reader announcements
- Accessibility audit findings

---

## Priority Rules

### 1) Prefer Native HTML

Use semantic elements already present in daily-todo and keep extending them:

- nav, main, section, table, thead, tbody, th, tr, td, form, label, input, select, textarea, button

Do not replace native controls with div-based controls.

### 2) Keyboard Access Is Mandatory

Verify for all interactive controls:

- Tab and Shift+Tab order is logical
- Enter and Space activate relevant controls
- Links and buttons are both reachable and visible on focus
- No mouse-only interaction for filter apply/reset, row actions, or form submission

### 3) Focus Visibility And Management

daily-todo currently uses visible focus styles on inputs. Preserve and extend this to all interactive elements:

- primary-button
- secondary-button
- inline-button
- status-button
- app-nav links

Never remove focus outlines unless replacing them with an equally visible custom style.

### 4) Accessible Names

Every control must have a programmatic name:

- Keep label + for/id pairs for all form fields
- Icon-only buttons must include aria-label
- Action links and buttons in tables should have unique accessible names when repeated

### 5) Forms And Validation

For add-todo.html and edit-todo.html:

- Keep visible labels for title, description, priority, status, category, dueDate
- Mark required fields with required and, when needed, aria-required
- Render field-level errors near fields
- Connect error text with aria-describedby
- Mark invalid controls with aria-invalid="true"
- Use role="alert" or assertive live region for submit-blocking errors

### 6) Semantic Page Structure

Each page should keep one h1 and logical heading flow:

- h1 page title
- h2 section titles (summary cards, recent table, etc.)

Avoid skipped heading levels unless structurally justified.

### 7) Tables Must Be Data Tables

For recent, todos, and history tables:

- Keep th header cells
- Add table caption when context is not obvious from nearby heading
- Keep actions in dedicated column with clear header label
- Add scope="col" on header cells when updating table markup

### 8) Dynamic JavaScript Announcements

daily-todo already uses polite live regions (feedback-message and counters). Continue this pattern:

- Announce load results (for example, TODO item(s) loaded)
- Announce create/update/delete outcomes
- Ensure announcements are concise and meaningful
- Avoid frequent noisy updates during typing unless necessary

When replacing table rows, ensure focus is not unexpectedly lost.

### 9) Actions In Dynamic Rows

In ui.js table actions, ensure accessible names are unique per row, for example:

- Edit TODO: [title]
- Mark TODO complete: [title]
- Delete TODO: [title]

This improves screen-reader navigation in repeated action groups.

### 10) Navigation And Skip Link

All pages have a shared header/nav. Ensure:

- nav has an accessible label
- Current page uses aria-current="page"
- A skip link exists and targets main content id

Recommended pattern:

- Add skip link before header
- Add id="main-content" on main container

### 11) Color, Contrast, And Non-Color Cues

Priority and status badges must not rely on color alone:

- Keep text labels (LOW, HIGH, COMPLETED, etc.)
- Ensure contrast for badge text and feedback text meets AA
- Error/success states should use text + style, not color only

### 12) Reduced Motion

If animations/transitions are introduced, support:

- @media (prefers-reduced-motion: reduce)

### 13) Backend Validation Payloads

Current backend errors are plain message strings (for example, "Title is required.").

When improving API responses, prefer field-aware error payloads that UI can map to controls:

- field
- message
- optional code

This enables reliable field-level announcement and aria-describedby linking.

---

## daily-todo Review Checklist

### HTML

- One h1 per page
- Labeled controls
- Main landmark present
- Skip link present
- Data tables have headers (and captions when useful)

### Keyboard

- Can reach all filter controls and table actions
- Can submit forms from keyboard
- Focus indicator visible on links, buttons, inputs, selects, textareas

### Forms

- Required state is clear for title
- Invalid fields expose aria-invalid
- Error message linked via aria-describedby
- Submit errors announced clearly

### JavaScript

- Live region updates for load/save/delete errors and success
- Focus remains predictable after dynamic updates
- No click-only behavior without keyboard equivalent

### Visual

- Contrast checks for text, badges, buttons, links, and feedback message
- Overdue row highlighting still readable with text alone

### Backend

- Error messages are meaningful
- Validation is specific enough for field mapping

---

## Minimal Safe Fix Patterns

### Pattern A: Add Skip Link And Main Target

- Add a skip link at top of body
- Add id="main-content" to main

### Pattern B: Field Error Wiring

- Add small error element under each field
- Toggle aria-invalid and aria-describedby in JS
- Move focus to first invalid field after submit

### Pattern C: Better Action Labels In Tables

- In ui.js row rendering, include todo title in aria-label for repeated actions

### Pattern D: Focus Styling Coverage

- Add :focus-visible styles for anchors and all button classes
- Keep color contrast >= AA requirements

---

## Review Process

### Step 1: Discover

Identify interactive controls on:

- add-todo.html
- edit-todo.html
- todos.html
- index.html
- history.html

### Step 2: Audit

Review by category:

- Keyboard
- Focus
- Semantics and ARIA
- Forms and validation
- Dynamic behavior and announcements
- Visual contrast and non-color cues

### Step 3: Fix

Apply minimal safe changes:

- Native HTML first
- Small CSS additions
- Small JS updates in app.js/ui.js/utils.js
- Optional incremental backend payload improvements

---

## Output Format For Accessibility Reviews

Use this structure when reporting findings:

### Summary

- 2 to 5 high-impact findings

### Issues Found

#### Keyboard
#### Focus
#### Semantics And ARIA
#### Forms And Validation
#### JavaScript Behavior
#### Visual Accessibility
#### Backend Validation Contract

### Proposed Fixes

- Minimal patch-oriented suggestions
- Include exact files and small code updates

### How To Test

#### Keyboard Test

1. Tab through all pages and controls.
2. Verify visible focus on every interactive element.
3. Trigger Apply/Reset/Create/Save/Delete/Mark Complete from keyboard.
4. Confirm focus location after each dynamic update.

#### Screen Reader Test

Verify name, role, and state for:

- Navigation links
- Form fields
- Required and invalid states
- Table actions
- Live region updates

#### Automated Test

Use:

- Lighthouse
- axe DevTools
- WAVE

---

## Success Criteria

A change passes when all are true:

- Fully keyboard operable
- Screen-reader compatible across all TODO pages
- No critical Lighthouse accessibility issues
- No critical axe violations
- Focus behavior is predictable
- Form validation is perceivable, operable, and understandable
- WCAG 2.1 AA expectations are met for current scope

