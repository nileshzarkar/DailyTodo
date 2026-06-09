const sampleTodos = [
    { id: 1, title: "Prepare sprint notes", priority: "HIGH", status: "PENDING", dueDate: "2026-06-10" },
    { id: 2, title: "Review pull request", priority: "MEDIUM", status: "IN_PROGRESS", dueDate: "2026-06-11" },
    { id: 3, title: "Archive completed tasks", priority: "LOW", status: "COMPLETED", dueDate: "2026-06-08" }
];

function setText(id, message) {
    const element = document.getElementById(id);
    if (element) {
        element.textContent = message || "";
    }
}

function clearFieldError(fieldId) {
    const input = document.getElementById(fieldId);
    const error = document.getElementById(`${fieldId}-error`);

    if (input instanceof HTMLElement) {
        input.removeAttribute("aria-invalid");
    }
    if (error) {
        error.textContent = "";
        error.hidden = true;
    }
}

function setFieldError(fieldId, message) {
    const input = document.getElementById(fieldId);
    const error = document.getElementById(`${fieldId}-error`);

    if (input instanceof HTMLElement) {
        input.setAttribute("aria-invalid", "true");
    }
    if (error) {
        error.textContent = message;
        error.hidden = false;
    }
}

function validateForm(form) {
    const formData = new FormData(form);
    const errors = [];

    clearFieldError("title");
    clearFieldError("description");
    clearFieldError("dueDate");

    const title = (formData.get("title") || "").toString().trim();
    const description = (formData.get("description") || "").toString();
    const dueDate = (formData.get("dueDate") || "").toString();

    if (!title) {
        errors.push({ field: "title", message: "Title is required." });
    }
    if (description.length > 500) {
        errors.push({ field: "description", message: "Description must be 500 characters or fewer." });
    }
    if (dueDate && dueDate < "2024-01-01") {
        errors.push({ field: "dueDate", message: "Due date is outside the allowed range." });
    }

    return errors;
}

function setupFormExample() {
    const form = document.getElementById("example-todo-form");
    if (!(form instanceof HTMLFormElement)) {
        return;
    }

    const resetButton = document.getElementById("reset-form");

    form.addEventListener("submit", event => {
        event.preventDefault();
        setText("form-error-summary", "");
        setText("form-announcement", "");

        const errors = validateForm(form);
        if (errors.length > 0) {
            errors.forEach(error => setFieldError(error.field, error.message));
            setText("form-error-summary", "Please fix the highlighted fields and try again.");

            const firstError = document.getElementById(errors[0].field);
            if (firstError instanceof HTMLElement) {
                firstError.focus();
            }
            return;
        }

        form.reset();
        setText("form-announcement", "TODO created successfully.");
    });

    if (resetButton instanceof HTMLButtonElement) {
        resetButton.addEventListener("click", () => {
            form.reset();
            clearFieldError("title");
            clearFieldError("description");
            clearFieldError("dueDate");
            setText("form-error-summary", "");
            setText("form-announcement", "Form has been reset.");
        });
    }
}

function renderTableRows() {
    const tableBody = document.getElementById("example-todos-body");
    const tableRegion = document.getElementById("table-region");
    if (!(tableBody instanceof HTMLElement) || !(tableRegion instanceof HTMLElement)) {
        return;
    }

    tableRegion.setAttribute("aria-busy", "true");

    tableBody.innerHTML = sampleTodos
        .map(todo => {
            const safeTitle = todo.title.replace(/"/g, "&quot;");
            return `
                <tr>
                    <td>${safeTitle}</td>
                    <td><span class="badge priority">${todo.priority}</span></td>
                    <td><span class="badge status">${todo.status.replace("_", " ")}</span></td>
                    <td>${todo.dueDate}</td>
                    <td>
                        <div class="todo-actions">
                            <button type="button" class="inline-button" aria-label="Edit TODO: ${safeTitle}">Edit</button>
                            <button type="button" class="status-button" aria-label="Mark TODO complete: ${safeTitle}">Mark Complete</button>
                            <button type="button" class="inline-button" aria-label="Delete TODO: ${safeTitle}">Delete</button>
                        </div>
                    </td>
                </tr>
            `;
        })
        .join("");

    tableRegion.setAttribute("aria-busy", "false");
    setText("table-feedback", `${sampleTodos.length} TODO item(s) loaded.`);
}

setupFormExample();
renderTableRows();
