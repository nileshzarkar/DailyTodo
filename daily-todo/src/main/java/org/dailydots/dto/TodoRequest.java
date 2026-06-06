package org.dailydots.dto;

import java.time.LocalDate;
import org.dailydots.entity.TodoPriority;
import org.dailydots.entity.TodoStatus;

/**
 * Request payload used to create or update TODO items.
 */
public class TodoRequest {

    private String title;
    private String description;
    private TodoPriority priority;
    private TodoStatus status;
    private String category;
    private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoPriority getPriority() {
        return priority;
    }

    public void setPriority(TodoPriority priority) {
        this.priority = priority;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
