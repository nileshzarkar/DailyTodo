export function formatDate(isoDate) {
    if (!isoDate) {
        return "-";
    }

    const parsed = new Date(isoDate);
    if (Number.isNaN(parsed.getTime())) {
        return "-";
    }

    return parsed.toLocaleDateString();
}

export function formatDateTime(isoDateTime) {
    if (!isoDateTime) {
        return "-";
    }

    const parsed = new Date(isoDateTime);
    if (Number.isNaN(parsed.getTime())) {
        return "-";
    }

    return parsed.toLocaleString();
}

export function setFeedback(element, message, tone = "") {
    if (!element) {
        return;
    }
    element.textContent = message || "";
    element.classList.remove("error", "success");
    if (tone) {
        element.classList.add(tone);
    }
}

export function getQueryParam(key) {
    const params = new URLSearchParams(window.location.search);
    return params.get(key);
}

export function buildQueryString(queryObject) {
    const params = new URLSearchParams();

    Object.entries(queryObject).forEach(([key, value]) => {
        if (value !== null && value !== undefined && value !== "") {
            params.set(key, value);
        }
    });

    return params.toString();
}

export function escapeHtml(value) {
    if (value === null || value === undefined) {
        return "";
    }

    return String(value)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/\"/g, "&quot;")
        .replace(/'/g, "&#039;");
}
