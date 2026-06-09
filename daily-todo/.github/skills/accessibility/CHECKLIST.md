# Accessibility Checklist For DailyDots (Quick)

Use this checklist when reviewing or implementing UI changes in DailyDots.

Project scope:

- Quarkus REST backend
- HTML pages in src/main/resources/META-INF/resources
- JavaScript modules in src/main/resources/META-INF/resources/js
- CSS files in src/main/resources/META-INF/resources/css

---

## A) Semantics (HTML First)

- [ ] Interactive controls are native elements (button, a, input, select, textarea)
- [ ] No clickable div/span used as controls
- [ ] Exactly one h1 per page (index, add-todo, edit-todo, todos, history)
- [ ] Heading order is logical (h1 to h2 to h3)
- [ ] Main content is inside main landmark
- [ ] Navigation uses nav with a clear label
- [ ] Tables are used only for tabular data
- [ ] Table headers are marked with th (and scope="col" when updated)
- [ ] Table caption is added when table purpose is not obvious

---

## B) Keyboard

- [ ] All links, buttons, form fields, and row actions are reachable with Tab
- [ ] Tab order follows visual order on all pages
- [ ] Enter and Space activate action buttons (Apply, Reset, Create, Save, Delete, Mark Complete)
- [ ] Filter and form interactions are fully usable without a mouse
- [ ] No keyboard traps
- [ ] If dialog/menu is introduced, Escape closes it

---

## C) Focus

- [ ] Visible focus indicator exists for links, buttons, inputs, selects, and textareas
- [ ] Focus style is not removed without an accessible replacement
- [ ] After dynamic updates (table refresh, form submit), focus remains predictable
- [ ] If modal/dialog is added, focus moves inside on open and returns to trigger on close
- [ ] If modal/dialog is added, background content is not focusable while open

---

## D) Accessible Names, Labels, Instructions

- [ ] Every form control has a visible label associated with for/id
- [ ] Required fields (title) are clearly indicated
- [ ] Repeated row actions include unique accessible names (for example: Delete TODO: Buy milk)
- [ ] Icon-only controls include aria-label
- [ ] Character counters are associated using aria-describedby
- [ ] Help text and error text are connected to fields via aria-describedby

---

## E) Forms And Validation (Add/Edit TODO)

- [ ] Title required validation is announced clearly
- [ ] Invalid fields set aria-invalid="true"
- [ ] Field-level error message appears near the invalid field
- [ ] Submit-blocking errors are announced with role="alert" or assertive live region
- [ ] Success and non-blocking messages use polite announcement behavior
- [ ] Date, category, status, and priority fields remain keyboard operable

---

## F) ARIA (Use Only When Needed)

- [ ] Native semantics are preferred before adding ARIA
- [ ] aria-expanded always matches real state (if expandable UI is added)
- [ ] aria-controls points to an existing id (if used)
- [ ] role values are correct for custom patterns (dialog, menu, tablist)
- [ ] aria-modal is used only for actual modal dialogs

---

## G) Dynamic JavaScript Behavior

- [ ] Live feedback text updates for loading, success, and errors are announced appropriately
- [ ] TODO list load count messages are meaningful and concise
- [ ] Table rerendering does not unexpectedly drop user context
- [ ] Async failures from api.js are surfaced as readable messages
- [ ] No state change is announced only by color change

---

## H) Visual Accessibility

- [ ] Text and UI contrast meets WCAG 2.1 AA
- [ ] Error, success, and overdue states use text plus visual style (not color alone)
- [ ] Link and button states are distinguishable (default, hover, focus, active)
- [ ] Overdue row styling remains readable at high zoom
- [ ] Content remains usable at 200% zoom without hidden essential information

---

## I) Reduced Motion

- [ ] Any animation/transition respects prefers-reduced-motion
- [ ] No essential information depends only on motion

---

## J) Quarkus REST Validation Contract

- [ ] Backend validation errors are specific and actionable
- [ ] Errors map cleanly to form fields when possible
- [ ] Frontend can announce backend errors accessibly
- [ ] Avoid generic error-only responses when field details are available

---

## K) Quick Test Pass (Minimum)

- [ ] Keyboard-only pass across all pages: navigate, filter, create, edit, complete, delete
- [ ] Screen reader sanity pass: each control has correct name, role, state/value
- [ ] Lighthouse accessibility run has no critical issues
- [ ] axe DevTools scan has no critical violations
- [ ] Zoom to 200% still supports full task flow without clipping key actions

---

## L) Page-Specific Spot Checks

Dashboard (index.html)

- [ ] Summary cards are readable and updates are announced without noise
- [ ] Recent TODO table keeps proper headers and row readability

View TODOs (todos.html)

- [ ] Filters have labels and are keyboard-friendly
- [ ] Row actions have clear accessible names
- [ ] Feedback message reports load or failure states clearly

Add/Edit TODO (add-todo.html, edit-todo.html)

- [ ] Character counters are announced politely and are not disruptive
- [ ] Validation errors are linked to the right fields
- [ ] Submit success/failure announcements are clear

History (history.html)

- [ ] Table headers are clear and completion/overdue information is understandable
- [ ] Empty state message is announced and visible
