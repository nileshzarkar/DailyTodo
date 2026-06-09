package org.dailydots.examples.accessibility;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.dailydots.dto.TodoRequest;
import org.dailydots.dto.TodoResponse;
import org.dailydots.entity.Todo;
import org.dailydots.entity.TodoPriority;
import org.dailydots.entity.TodoStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Example service that separates business logic from the resource layer.
 */
@ApplicationScoped
public class TodoServiceExample {

    private final TodoRepositoryExample todoRepository;

    public TodoServiceExample(TodoRepositoryExample todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Returns field-level validation errors suitable for accessible form UIs.
     */
    public Optional<TodoValidationErrorResponseExample> validateForCreate(TodoRequest request) {
        TodoValidationErrorResponseExample response = new TodoValidationErrorResponseExample();
        response.setMessage("Validation failed. Please fix the highlighted fields.");

        if (request == null) {
            response.addFieldError("form", "Request body is required.");
            return Optional.of(response);
        }

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            response.addFieldError("title", "Title is required.");
        } else if (request.getTitle().trim().length() > 255) {
            response.addFieldError("title", "Title must be 255 characters or fewer.");
        }

        if (request.getDescription() != null && request.getDescription().length() > 2000) {
            response.addFieldError("description", "Description must be 2000 characters or fewer.");
        }

        if (request.getDueDate() != null && request.getDueDate().isBefore(LocalDate.now().minusYears(1))) {
            response.addFieldError("dueDate", "Due date is too far in the past.");
        }

        return response.getFieldErrors().isEmpty() ? Optional.empty() : Optional.of(response);
    }

    /**
     * Creates a TODO item after validation is handled by the resource.
     */
    @Transactional
    public TodoResponse create(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle().trim());
        todo.setDescription(request.getDescription());
        todo.setCategory(request.getCategory());
        todo.setDueDate(request.getDueDate());
        todo.setPriority(request.getPriority() == null ? TodoPriority.MEDIUM : request.getPriority());

        TodoStatus status = request.getStatus() == null ? TodoStatus.PENDING : request.getStatus();
        todo.setStatus(status);
        if (status == TodoStatus.COMPLETED) {
            todo.setCompletedDate(LocalDateTime.now());
        }

        todoRepository.persist(todo);
        return toResponse(todo, LocalDate.now());
    }

    private TodoResponse toResponse(Todo todo, LocalDate today) {
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setDescription(todo.getDescription());
        response.setCategory(todo.getCategory());
        response.setPriority(todo.getPriority());
        response.setStatus(todo.getStatus());
        response.setDueDate(todo.getDueDate());
        response.setCreatedDate(todo.getCreatedDate());
        response.setUpdatedDate(todo.getUpdatedDate());
        response.setCompletedDate(todo.getCompletedDate());
        response.setOverdue(
                todo.getStatus() != TodoStatus.COMPLETED
                        && todo.getDueDate() != null
                        && todo.getDueDate().isBefore(today)
        );
        return response;
    }
}
