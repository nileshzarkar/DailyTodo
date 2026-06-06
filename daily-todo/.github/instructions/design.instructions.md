````md
---
applyTo: "**/*.html,**/*.css,**/*.js"
---

# Design Instructions

## Purpose

These instructions define the UI and UX standards for the application.

The goals are to:

- Create a clean and intuitive user experience.
- Maintain visual consistency across all screens.
- Prioritize usability over visual complexity.
- Ensure responsive and accessible interfaces.
- Generate professional and maintainable UI designs.

---

# Design Principles

- Prioritize clarity over decoration.
- Keep interfaces simple and focused.
- Design for usability first and aesthetics second.
- Reduce unnecessary user actions.
- Present information in a clear and predictable manner.
- Avoid visual clutter.
- Use consistent patterns throughout the application.
- Maintain a professional and modern appearance.

---

# Layout Consistency

- Use consistent page layouts across the application.
- Align content using a predictable grid or layout structure.
- Keep page sections clearly separated.
- Maintain consistent spacing between related elements.
- Use cards, sections, and containers consistently.
- Keep forms and action buttons positioned predictably.
- Avoid layouts that shift unexpectedly.
- Ensure layouts remain usable on different screen sizes.

### Preferred Layout

```text
Header

Main Content
 ├── Form Section
 ├── Data Section
 └── Action Area

Footer (Optional)
````

---

# Spacing System

* Use a consistent spacing scale throughout the application.
* Maintain uniform padding and margins.
* Group related content visually.
* Separate unrelated content clearly.
* Avoid crowded interfaces.
* Avoid excessive empty space.

### Recommended Spacing Scale

```text
4px
8px
16px
24px
32px
48px
```

### Guidelines

* Small gaps: 4px–8px
* Component spacing: 16px
* Section spacing: 24px–32px
* Page spacing: 32px–48px

---

# Typography Hierarchy

* Use a clear visual hierarchy.
* Limit the number of font families.
* Maintain consistent font sizing.
* Ensure text remains readable on all devices.
* Use font weight and size to indicate importance.

### Recommended Hierarchy

```text
Page Title
  Largest

Section Title
  Medium

Body Text
  Standard

Helper Text
  Smaller
```

### Guidelines

* Use readable font sizes.
* Avoid excessively small text.
* Maintain sufficient line spacing.
* Use bold text sparingly for emphasis.

---

# Color Usage

* Use a limited and consistent color palette.
* Maintain sufficient contrast between text and backgrounds.
* Use color to communicate meaning, not decoration.
* Keep the interface visually balanced.
* Avoid excessive color variation.

### Suggested Color Roles

```text
Primary
  Main actions

Secondary
  Supporting actions

Success
  Completed operations

Warning
  Attention required

Error
  Failures and validation issues

Neutral
  Backgrounds and borders
```

### Guidelines

* Use one primary color consistently.
* Keep backgrounds clean and neutral.
* Avoid bright or distracting colors.
* Use status colors consistently.

---

# Component Consistency

* Use consistent styling across all components.
* Similar actions should look and behave similarly.
* Maintain consistent button styles.
* Maintain consistent form styling.
* Use predictable interaction patterns.
* Reuse existing components whenever possible.

### Components To Standardize

* Buttons
* Forms
* Inputs
* Labels
* Cards
* Tables
* Navigation
* Dialogs
* Notifications

---

# Forms and User Input

* Keep forms simple and easy to complete.
* Clearly label all inputs.
* Group related fields together.
* Show validation messages near the affected field.
* Provide clear submit and cancel actions.
* Avoid unnecessary fields.
* Use appropriate input types.

### Preferred

```text
Label
Input Field
Validation Message
```

---

# Accessibility

* Ensure sufficient color contrast.
* Support keyboard navigation.
* Use semantic HTML elements.
* Provide labels for form controls.
* Ensure interactive elements are accessible.
* Avoid relying solely on color to convey meaning.
* Use meaningful button and link text.
* Support screen readers where applicable.

### Examples

```html
<label for="title">Task Title</label>
<input id="title" />
```

Preferred over:

```html
<input placeholder="Task Title" />
```

---

# Responsive Design

* Design mobile-first where practical.
* Ensure layouts adapt to different screen sizes.
* Avoid fixed-width layouts.
* Use flexible containers and layouts.
* Test common viewport sizes.
* Ensure forms and buttons remain usable on smaller screens.

### Target Devices

* Mobile
* Tablet
* Desktop

---

# Feedback and Interaction

* Provide clear feedback for user actions.
* Display loading indicators when necessary.
* Display success messages for completed actions.
* Display meaningful error messages.
* Make interactive elements visually identifiable.
* Ensure hover and focus states are consistent.

---

# TODO Tracker Specific Guidelines

* Keep the interface focused on task management.
* Prioritize task visibility.
* Make adding tasks quick and simple.
* Make task completion actions obvious.
* Clearly distinguish completed and pending tasks.
* Minimize the number of clicks required for common actions.
* Avoid unnecessary dashboard widgets or visualizations.
* Focus on productivity and usability.

---

# Copilot Behavior

When generating HTML, CSS, or JavaScript UI code:

* Follow existing design patterns.
* Generate responsive layouts by default.
* Prioritize usability and accessibility.
* Use semantic HTML elements.
* Keep styling clean and maintainable.
* Reuse existing UI components whenever possible.
* Avoid unnecessary animations and visual effects.
* Generate professional, production-ready user interfaces.
* Maintain consistency with the application's existing design language.

```
```
