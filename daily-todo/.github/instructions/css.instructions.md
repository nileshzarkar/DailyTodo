````
---
applyTo: "**/*.css"
---

# CSS Coding Standards

## Purpose

These instructions define CSS coding standards for the project.

The goals are to:

- Maintain a consistent visual design.
- Improve readability and maintainability.
- Encourage reusable styling patterns.
- Reduce duplicate styles.
- Keep CSS simple and scalable.

---

## General Principles

- Prefer simple and maintainable CSS.
- Keep styles modular and reusable.
- Avoid unnecessary complexity.
- Prioritize readability over clever CSS techniques.
- Follow existing styling patterns before introducing new ones.
- Reuse existing classes whenever possible.

---

## Naming Conventions

- Use descriptive class names.
- Use kebab-case naming.

### Preferred Examples

```css
.todo-card
.todo-title
.todo-list
.primary-button
.completed-task
````

### Avoid

```css
.box1
.div2
.red-text
.tmp
.x
```

* Class names should describe purpose, not appearance.
* Avoid abbreviations unless widely understood.

---

## File Organization

* Keep related styles together.
* Group styles logically by component or feature.
* Organize styles in the following order:

```css
/* Variables */
/* Base Styles */
/* Layout */
/* Components */
/* Utilities */
```

* Avoid placing unrelated styles together.
* Keep files focused on a specific responsibility.

---

## Selectors

* Prefer class selectors over element selectors.
* Avoid excessive selector nesting.
* Keep selectors simple and easy to understand.
* Avoid overly specific selectors.
* Limit nesting depth to improve maintainability.

### Preferred

```css
.todo-card {
}

.todo-title {
}
```

### Avoid

```css
.container .content .wrapper .todo-card .title {
}
```

---

## Layout Guidelines

* Prefer Flexbox for most layouts.
* Use CSS Grid when working with two-dimensional layouts.
* Avoid unnecessary positioning.
* Use responsive layouts by default.
* Design for mobile and desktop screens.

### Preferred

```css
display: flex;
display: grid;
```

### Avoid

```css
float: left;
float: right;
```

unless maintaining legacy code.

---

## Styling Guidelines

* Use consistent spacing throughout the application.
* Use relative units where appropriate.
* Keep visual styles consistent.
* Avoid hardcoded values when reusable variables are available.
* Prefer reusable utility classes for common patterns.

### Preferred Units

```css
rem
em
%
vh
vw
```

### Use Pixels For

```css
borders
small fixed dimensions
```

when appropriate.

---

## Colors and Variables

* Use CSS variables for reusable values.
* Store common colors, spacing, and sizing variables centrally.
* Avoid repeating the same color values throughout the codebase.

### Preferred

```css
:root {
    --primary-color: #2563eb;
    --spacing-medium: 1rem;
}
```

### Avoid

```css
color: #2563eb;
```

repeated in multiple locations.

---

## Comments

* Comment only when necessary.
* Explain why, not what.
* Keep comments concise and accurate.
* Remove outdated comments.

### Good Comment

```css
/* Prevent layout shift while data is loading */
```

### Avoid

```css
/* Set display to flex */
display: flex;
```

---

## Responsive Design

* Design mobile-first where practical.
* Use responsive breakpoints consistently.
* Test layouts across multiple screen sizes.
* Avoid fixed-width layouts unless required.
* Ensure content remains accessible on smaller screens.

---

## Performance

* Avoid duplicate styles.
* Avoid unnecessary selectors.
* Keep CSS files organized and maintainable.
* Reuse existing styles before creating new ones.
* Remove unused CSS when safe to do so.

---

## Copilot Behavior

When generating CSS:

* Follow existing project styling patterns.
* Reuse existing classes whenever possible.
* Prefer Flexbox and Grid layouts.
* Generate responsive styles by default.
* Use CSS variables for reusable values.
* Avoid excessive nesting.
* Keep styles simple and maintainable.
* Generate clean, production-ready CSS.

```
```
