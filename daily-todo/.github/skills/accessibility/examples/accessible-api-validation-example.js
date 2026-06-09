function clearFormValidationState() {
    const errorElements = document.querySelectorAll(".field-error");
    errorElements.forEach(element => {
        element.textContent = "";
        element.hidden = true;
    });

    const invalidInputs = document.querySelectorAll("[aria-invalid='true']");
    invalidInputs.forEach(element => {
        element.removeAttribute("aria-invalid");
    });

    const summary = document.getElementById("form-error-summary");
    if (summary) {
        summary.textContent = "";
    }
}

function applyFieldValidationErrors(errorPayload) {
    const fieldErrors = errorPayload?.fieldErrors || [];
    if (!Array.isArray(fieldErrors) || fieldErrors.length === 0) {
        return;
    }

    fieldErrors.forEach(fieldError => {
        const fieldName = fieldError.field;
        const message = fieldError.message || "Invalid value.";

        const fieldElement = document.getElementById(fieldName);
        const errorElement = document.getElementById(`${fieldName}-error`);

        if (fieldElement) {
            fieldElement.setAttribute("aria-invalid", "true");
        }

        if (errorElement) {
            errorElement.textContent = message;
            errorElement.hidden = false;
        }
    });

    const firstInvalidField = document.getElementById(fieldErrors[0].field);
    if (firstInvalidField instanceof HTMLElement) {
        firstInvalidField.focus();
    }
}

function announceValidationSummary(errorPayload) {
    const summary = document.getElementById("form-error-summary");
    if (!summary) {
        return;
    }

    const fallbackMessage = "Please fix the highlighted fields and try again.";
    summary.textContent = errorPayload?.message || fallbackMessage;
}

async function mockCreateTodoRequest(requestBody) {
    const normalizedTitle = (requestBody?.title || "").trim();
    const normalizedDescription = requestBody?.description || "";

    if (!normalizedTitle || normalizedDescription.length > 500) {
        return {
            ok: false,
            status: 400,
            body: {
                message: "Validation failed. Please review the form fields.",
                fieldErrors: [
                    ...(!normalizedTitle ? [{ field: "title", message: "Title is required." }] : []),
                    ...(normalizedDescription.length > 500
                        ? [{ field: "description", message: "Description must be 500 characters or fewer." }]
                        : [])
                ]
            }
        };
    }

    return {
        ok: true,
        status: 201,
        body: {
            id: 101,
            title: normalizedTitle,
            description: normalizedDescription,
            status: requestBody?.status || "PENDING",
            priority: requestBody?.priority || "MEDIUM"
        }
    };
}

export async function submitTodoFormWithApiValidation(formElement) {
    const formData = new FormData(formElement);
    const payload = {
        title: (formData.get("title") || "").toString(),
        description: (formData.get("description") || "").toString(),
        priority: (formData.get("priority") || "MEDIUM").toString(),
        status: (formData.get("status") || "PENDING").toString(),
        dueDate: (formData.get("dueDate") || "").toString() || null
    };

    clearFormValidationState();

    const response = await mockCreateTodoRequest(payload);
    if (!response.ok && response.status === 400) {
        applyFieldValidationErrors(response.body);
        announceValidationSummary(response.body);
        return response;
    }

    const successRegion = document.getElementById("form-announcement");
    if (successRegion) {
        successRegion.textContent = "TODO created successfully.";
    }

    formElement.reset();
    return response;
}
