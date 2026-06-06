import { escapeHtml, formatDate, formatDateTime } from "./utils.js";

function createBadge(type, value) {
    return `<span class="${type}-badge ${escapeHtml(value)}">${escapeHtml(value.replace("_", " "))}</span>`;
}

export function renderSummary(summary) {
    document.getElementById("total-count").textContent = summary.total ?? 0;
    document.getElementById("pending-count").textContent = summary.pending ?? 0;
    document.getElementById("in-progress-count").textContent = summary.inProgress ?? 0;
    document.getElementById("completed-count").textContent = summary.completed ?? 0;
    document.getElementById("overdue-count").textContent = summary.overdue ?? 0;
}

export function renderRecentTodos(todos) {
    const tableBody = document.getElementById("recent-todos-body");
    if (!tableBody) {
        return;
    }

    if (todos.length === 0) {
        tableBody.innerHTML = "<tr><td colspan='5'>No TODO items yet.</td></tr>";
        return;
    }

    tableBody.innerHTML = todos
        .map(todo => `
            <tr class="${todo.overdue ? "todo-overdue" : ""}">
                <td>${escapeHtml(todo.title)}</td>
                <td>${createBadge("priority", todo.priority)}</td>
                <td>${createBadge("status", todo.status)}</td>
                <td>${escapeHtml(todo.category || "-")}</td>
                <td>${formatDate(todo.dueDate)}</td>
            </tr>
        `)
        .join("");
}

export function renderTodosTable(todos) {
    const tableBody = document.getElementById("todos-table-body");
    if (!tableBody) {
        return;
    }

    if (todos.length === 0) {
        tableBody.innerHTML = "<tr><td colspan='7'>No TODO items match the selected filters.</td></tr>";
        return;
    }

    tableBody.innerHTML = todos
        .map(todo => `
            <tr class="${todo.overdue ? "todo-overdue" : ""}">
                <td>${escapeHtml(todo.title)}</td>
                <td>${createBadge("priority", todo.priority)}</td>
                <td>${createBadge("status", todo.status)}</td>
                <td>${escapeHtml(todo.category || "-")}</td>
                <td>${formatDate(todo.dueDate)}</td>
                <td>${formatDateTime(todo.createdDate)}</td>
                <td>
                    <div class="todo-actions">
                        <a class="inline-button" href="/edit-todo.html?id=${todo.id}">Edit</a>
                        ${todo.status !== "COMPLETED" ? `<button class="status-button" type="button" data-action="complete" data-id="${todo.id}">Mark Complete</button>` : ""}
                        <button class="inline-button" type="button" data-action="delete" data-id="${todo.id}">Delete</button>
                    </div>
                </td>
            </tr>
        `)
        .join("");
}

export function renderHistoryTable(todos) {
    const tableBody = document.getElementById("history-table-body");
    if (!tableBody) {
        return;
    }

    if (todos.length === 0) {
        tableBody.innerHTML = "<tr><td colspan='5'>No historical TODO items available.</td></tr>";
        return;
    }

    tableBody.innerHTML = todos
        .map(todo => `
            <tr class="${todo.overdue ? "todo-overdue" : ""}">
                <td>${escapeHtml(todo.title)}</td>
                <td>${createBadge("status", todo.status)}</td>
                <td>${createBadge("priority", todo.priority)}</td>
                <td>${formatDate(todo.dueDate)}</td>
                <td>${formatDateTime(todo.completedDate)}</td>
            </tr>
        `)
        .join("");
}

export function fillTodoForm(todo) {
    document.getElementById("todoId").value = String(todo.id || "");
    document.getElementById("title").value = todo.title || "";
    document.getElementById("description").value = todo.description || "";
    document.getElementById("priority").value = todo.priority || "MEDIUM";
    document.getElementById("status").value = todo.status || "PENDING";
    document.getElementById("category").value = todo.category || "";
    document.getElementById("dueDate").value = todo.dueDate || "";
}

export function collectTodoFormData(formElement) {
    const formData = new FormData(formElement);
    return {
        title: (formData.get("title") || "").toString().trim(),
        description: (formData.get("description") || "").toString().trim(),
        priority: formData.get("priority"),
        status: formData.get("status"),
        category: (formData.get("category") || "").toString().trim(),
        dueDate: formData.get("dueDate") || null
    };
}
