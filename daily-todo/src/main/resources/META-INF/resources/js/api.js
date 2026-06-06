const API_BASE_URL = "/api/todos";

async function request(url, options = {}) {
    const response = await fetch(url, {
        headers: {
            "Content-Type": "application/json"
        },
        ...options
    });

    if (!response.ok) {
        const message = await response.text();
        throw new Error(message || "Request failed.");
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}

export function getTodos(queryString = "") {
    const url = queryString ? `${API_BASE_URL}?${queryString}` : API_BASE_URL;
    return request(url);
}

export function getTodoById(id) {
    return request(`${API_BASE_URL}/${id}`);
}

export function getSummary() {
    return request(`${API_BASE_URL}/summary`);
}

export function getHistory() {
    return request(`${API_BASE_URL}/history`);
}

export function createTodo(payload) {
    return request(API_BASE_URL, {
        method: "POST",
        body: JSON.stringify(payload)
    });
}

export function updateTodo(id, payload) {
    return request(`${API_BASE_URL}/${id}`, {
        method: "PUT",
        body: JSON.stringify(payload)
    });
}

export function updateTodoStatus(id, status) {
    return request(`${API_BASE_URL}/${id}/status?status=${encodeURIComponent(status)}`, {
        method: "PATCH"
    });
}

export function deleteTodo(id) {
    return request(`${API_BASE_URL}/${id}`, {
        method: "DELETE"
    });
}
