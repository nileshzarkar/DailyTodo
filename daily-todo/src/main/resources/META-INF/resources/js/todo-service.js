import {
    createTodo,
    deleteTodo,
    getHistory,
    getSummary,
    getTodoById,
    getTodos,
    updateTodo,
    updateTodoStatus
} from "./api.js";
import { buildQueryString } from "./utils.js";

export function fetchDashboardSummary() {
    return getSummary();
}

export async function fetchRecentTodos() {
    const queryString = buildQueryString({
        includeCompleted: true,
        showPastOnly: false,
        sortBy: "createdDate",
        sortDir: "desc"
    });

    const todos = await getTodos(queryString);
    return todos.slice(0, 7);
}

export function fetchTodos(filters) {
    const queryString = buildQueryString(filters);
    return getTodos(queryString);
}

export function fetchPastTodos() {
    return getHistory();
}

export function fetchTodo(id) {
    return getTodoById(id);
}

export function addTodo(payload) {
    return createTodo(payload);
}

export function saveTodo(id, payload) {
    return updateTodo(id, payload);
}

export function removeTodo(id) {
    return deleteTodo(id);
}

export function markTodoComplete(id) {
    return updateTodoStatus(id, "COMPLETED");
}
