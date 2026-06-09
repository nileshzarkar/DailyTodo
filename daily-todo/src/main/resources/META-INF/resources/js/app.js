import {
    addTodo,
    fetchDashboardSummary,
    fetchPastTodos,
    fetchRecentTodos,
    fetchTodo,
    fetchTodos,
    markTodoComplete,
    removeTodo,
    saveTodo
} from "./todo-service.js";
import {
    collectTodoFormData,
    fillTodoForm,
    renderHistoryTable,
    renderRecentTodos,
    renderSummary,
    renderTodosTable
} from "./ui.js";
import { getQueryParam, setFeedback } from "./utils.js";

async function initDashboardPage() {
    const [summary, recentTodos] = await Promise.all([
        fetchDashboardSummary(),
        fetchRecentTodos()
    ]);
    renderSummary(summary);
    renderRecentTodos(recentTodos);
}

function getFiltersFromUi() {
    return {
        search: document.getElementById("search")?.value.trim(),
        status: document.getElementById("statusFilter")?.value,
        priority: document.getElementById("priorityFilter")?.value,
        category: document.getElementById("categoryFilter")?.value.trim(),
        includeCompleted: document.getElementById("includeCompleted")?.checked ?? true,
        showPastOnly: document.getElementById("showPastOnly")?.checked ?? false,
        sortBy: document.getElementById("sortBy")?.value || "createdDate",
        sortDir: document.getElementById("sortDir")?.value || "desc"
    };
}

async function loadTodosWithFilters() {
    const feedback = document.getElementById("todos-feedback");
    try {
        const todos = await fetchTodos(getFiltersFromUi());
        renderTodosTable(todos);
        setFeedback(feedback, `${todos.length} TODO item(s) loaded.`);
    } catch (error) {
        setFeedback(feedback, error.message, "error");
    }
}

function setupTodoTableActions() {
    const tableBody = document.getElementById("todos-table-body");
    if (!tableBody) {
        return;
    }

    tableBody.addEventListener("click", async event => {
        const target = event.target;
        if (!(target instanceof HTMLElement)) {
            return;
        }

        const action = target.dataset.action;
        const idValue = target.dataset.id;
        const id = Number(idValue);
        if (!action || !id) {
            return;
        }

        const feedback = document.getElementById("todos-feedback");

        try {
            if (action === "delete") {
                await removeTodo(id);
                setFeedback(feedback, "TODO deleted.", "success");
            } else if (action === "complete") {
                await markTodoComplete(id);
                setFeedback(feedback, "TODO marked as completed.", "success");
            }

            await loadTodosWithFilters();
        } catch (error) {
            setFeedback(feedback, error.message, "error");
        }
    });
}

function setupTodoFilters() {
    document.getElementById("applyFilters")?.addEventListener("click", loadTodosWithFilters);

    document.getElementById("resetFilters")?.addEventListener("click", () => {
        document.getElementById("search").value = "";
        document.getElementById("statusFilter").value = "";
        document.getElementById("priorityFilter").value = "";
        document.getElementById("categoryFilter").value = "";
        document.getElementById("sortBy").value = "createdDate";
        document.getElementById("sortDir").value = "desc";
        document.getElementById("includeCompleted").checked = true;
        document.getElementById("showPastOnly").checked = false;
        loadTodosWithFilters();
    });
}

function getInputMaxLength(inputElement, fallbackLength) {
    if (!(inputElement instanceof HTMLInputElement || inputElement instanceof HTMLTextAreaElement)) {
        return fallbackLength;
    }

    const maxLengthValue = Number(inputElement.dataset.maxlength);
    return Number.isFinite(maxLengthValue) && maxLengthValue > 0 ? maxLengthValue : fallbackLength;
}

function ensureInputDescribedByCounter(inputElement, counterElement) {
    if (!(inputElement instanceof HTMLInputElement || inputElement instanceof HTMLTextAreaElement)) {
        return;
    }
    if (!(counterElement instanceof HTMLElement) || !counterElement.id) {
        return;
    }
    if (!inputElement.getAttribute("aria-describedby")) {
        inputElement.setAttribute("aria-describedby", counterElement.id);
    }
}

function bindCharacterCounter(inputElement, counterElement, maxLength) {
    if (!(inputElement instanceof HTMLInputElement || inputElement instanceof HTMLTextAreaElement)) {
        return;
    }
    if (!(counterElement instanceof HTMLElement)) {
        return;
    }

    function updateCharacterCounter() {
        if (inputElement.value.length > maxLength) {
            inputElement.value = inputElement.value.slice(0, maxLength);
        }

        counterElement.textContent = `${inputElement.value.length}/${maxLength} characters`;
    }

    inputElement.maxLength = maxLength;
    inputElement.addEventListener("input", updateCharacterCounter);
    updateCharacterCounter();

    return updateCharacterCounter;
}

function setupCreateForm() {
    const form = document.getElementById("todo-form");
    if (!form) {
        return;
    }

    const feedback = document.getElementById("form-feedback");
    const titleInput = document.getElementById("title");
    const titleCharacterCounter = document.getElementById("title-character-counter");
    const descriptionInput = document.getElementById("description");
    const descriptionCharacterCounter = document.getElementById("description-character-counter");
    const titleMaxLength = getInputMaxLength(titleInput, 50);
    const descriptionMaxLength = getInputMaxLength(descriptionInput, 500);

    ensureInputDescribedByCounter(titleInput, titleCharacterCounter);
    ensureInputDescribedByCounter(descriptionInput, descriptionCharacterCounter);

    const updateTitleCharacterCounter = bindCharacterCounter(titleInput, titleCharacterCounter, titleMaxLength);
    const updateDescriptionCharacterCounter = bindCharacterCounter(descriptionInput, descriptionCharacterCounter, descriptionMaxLength);

    form.addEventListener("submit", async event => {
        event.preventDefault();
        const payload = collectTodoFormData(form);

        if (payload.title.length > titleMaxLength) {
            payload.title = payload.title.slice(0, titleMaxLength);
        }
        if (payload.description.length > descriptionMaxLength) {
            payload.description = payload.description.slice(0, descriptionMaxLength);
        }

        if (!payload.title) {
            setFeedback(feedback, "Title is required.", "error");
            return;
        }

        try {
            await addTodo(payload);
            setFeedback(feedback, "TODO created successfully.", "success");
            form.reset();
            updateTitleCharacterCounter?.();
            updateDescriptionCharacterCounter?.();
        } catch (error) {
            setFeedback(feedback, error.message, "error");
        }
    });
}

async function setupEditForm() {
    const form = document.getElementById("todo-form");
    if (!form) {
        return;
    }

    const feedback = document.getElementById("form-feedback");
    const titleInput = document.getElementById("title");
    const titleCharacterCounter = document.getElementById("title-character-counter");
    const descriptionInput = document.getElementById("description");
    const descriptionCharacterCounter = document.getElementById("description-character-counter");
    const titleMaxLength = getInputMaxLength(titleInput, 255);
    const descriptionMaxLength = getInputMaxLength(descriptionInput, 2000);

    ensureInputDescribedByCounter(titleInput, titleCharacterCounter);
    ensureInputDescribedByCounter(descriptionInput, descriptionCharacterCounter);

    const updateTitleCharacterCounter = bindCharacterCounter(titleInput, titleCharacterCounter, titleMaxLength);
    const updateDescriptionCharacterCounter = bindCharacterCounter(descriptionInput, descriptionCharacterCounter, descriptionMaxLength);

    const todoId = Number(getQueryParam("id"));
    if (!todoId) {
        setFeedback(feedback, "Missing TODO id in query string.", "error");
        return;
    }

    try {
        const todo = await fetchTodo(todoId);
        fillTodoForm(todo);
        updateTitleCharacterCounter?.();
        updateDescriptionCharacterCounter?.();
    } catch (error) {
        setFeedback(feedback, error.message, "error");
        return;
    }

    form.addEventListener("submit", async event => {
        event.preventDefault();
        const payload = collectTodoFormData(form);

        if (payload.title.length > titleMaxLength) {
            payload.title = payload.title.slice(0, titleMaxLength);
        }
        if (payload.description.length > descriptionMaxLength) {
            payload.description = payload.description.slice(0, descriptionMaxLength);
        }

        if (!payload.title) {
            setFeedback(feedback, "Title is required.", "error");
            return;
        }

        try {
            await saveTodo(todoId, payload);
            setFeedback(feedback, "TODO updated successfully.", "success");
        } catch (error) {
            setFeedback(feedback, error.message, "error");
        }
    });
}

async function initTodosPage() {
    setupTodoFilters();
    setupTodoTableActions();
    await loadTodosWithFilters();
}

async function initHistoryPage() {
    const feedback = document.getElementById("history-feedback");
    try {
        const todos = await fetchPastTodos();
        renderHistoryTable(todos);
        setFeedback(feedback, `${todos.length} historical item(s) loaded.`);
    } catch (error) {
        setFeedback(feedback, error.message, "error");
    }
}

async function run() {
    const page = document.body.dataset.page;

    try {
        if (page === "dashboard") {
            await initDashboardPage();
        } else if (page === "add-todo") {
            setupCreateForm();
        } else if (page === "todos") {
            await initTodosPage();
        } else if (page === "edit-todo") {
            await setupEditForm();
        } else if (page === "history") {
            await initHistoryPage();
        }
    } catch (error) {
        console.error(error);
    }
}

run();
