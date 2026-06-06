package org.dailydots.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import org.dailydots.dto.TodoRequest;
import org.dailydots.dto.TodoResponse;
import org.dailydots.dto.TodoSummaryResponse;
import org.dailydots.entity.Todo;
import org.dailydots.entity.TodoPriority;
import org.dailydots.entity.TodoStatus;
import org.dailydots.repository.TodoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

/**
 * Business operations for TODO management.
 */
@ApplicationScoped
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Returns TODO items using search, filter and sort criteria.
     */
    public List<TodoResponse> listTodos(
            String search,
            TodoStatus status,
            TodoPriority priority,
            String category,
            boolean includeCompleted,
            boolean showPastOnly,
            String sortBy,
            String sortDir
    ) {
        LocalDate today = LocalDate.now();
        String normalizedSearch = search == null ? "" : search.trim().toLowerCase(Locale.ROOT);
        String normalizedCategory = category == null ? "" : category.trim().toLowerCase(Locale.ROOT);

        return todoRepository.listSorted(sortBy, sortDir)
                .stream()
                .filter(todo -> matchesSearch(todo, normalizedSearch))
                .filter(todo -> matchesStatus(todo, status))
                .filter(todo -> matchesPriority(todo, priority))
                .filter(todo -> matchesCategory(todo, normalizedCategory))
                .filter(todo -> includeCompleted || todo.getStatus() != TodoStatus.COMPLETED)
                .filter(todo -> !showPastOnly || isPastTodo(todo, today))
                .map(todo -> toResponse(todo, today))
                .toList();
    }

    /**
     * Returns one TODO item by id.
     */
    public TodoResponse getById(Long id) {
        Todo todo = todoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("TODO item not found."));
        return toResponse(todo, LocalDate.now());
    }

    /**
     * Returns completed and overdue items.
     */
    public List<TodoResponse> getHistory() {
        LocalDate today = LocalDate.now();
        return todoRepository.listAll()
                .stream()
                .filter(todo -> isPastTodo(todo, today))
                .map(todo -> toResponse(todo, today))
                .toList();
    }

    /**
     * Creates a TODO item.
     */
    @Transactional
    public TodoResponse create(TodoRequest request) {
        validateRequest(request);

        Todo todo = new Todo();
        applyRequest(todo, request);
        todoRepository.persist(todo);

        return toResponse(todo, LocalDate.now());
    }

    /**
     * Updates a TODO item by id.
     */
    @Transactional
    public TodoResponse update(Long id, TodoRequest request) {
        validateRequest(request);

        Todo todo = todoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("TODO item not found."));
        applyRequest(todo, request);

        return toResponse(todo, LocalDate.now());
    }

    /**
     * Deletes a TODO item by id.
     */
    @Transactional
    public void delete(Long id) {
        boolean deleted = todoRepository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("TODO item not found.");
        }
    }

    /**
     * Updates only the status of a TODO item.
     */
    @Transactional
    public TodoResponse updateStatus(Long id, TodoStatus status) {
        if (status == null) {
            throw new WebApplicationException("Status is required.", 400);
        }

        Todo todo = todoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("TODO item not found."));

        todo.setStatus(status);
        if (status == TodoStatus.COMPLETED) {
            todo.setCompletedDate(LocalDateTime.now());
        } else {
            todo.setCompletedDate(null);
        }

        return toResponse(todo, LocalDate.now());
    }

    /**
     * Returns aggregated TODO statistics.
     */
    public TodoSummaryResponse summary() {
        LocalDate today = LocalDate.now();
        List<Todo> todos = todoRepository.listAll();

        long pendingCount = todos.stream().filter(todo -> todo.getStatus() == TodoStatus.PENDING).count();
        long inProgressCount = todos.stream().filter(todo -> todo.getStatus() == TodoStatus.IN_PROGRESS).count();
        long completedCount = todos.stream().filter(todo -> todo.getStatus() == TodoStatus.COMPLETED).count();
        long overdueCount = todos.stream()
                .filter(todo -> todo.getStatus() != TodoStatus.COMPLETED)
                .filter(todo -> todo.getDueDate() != null && todo.getDueDate().isBefore(today))
                .count();

        TodoSummaryResponse response = new TodoSummaryResponse();
        response.setTotal(todos.size());
        response.setPending(pendingCount);
        response.setInProgress(inProgressCount);
        response.setCompleted(completedCount);
        response.setOverdue(overdueCount);
        return response;
    }

    private boolean matchesSearch(Todo todo, String search) {
        if (search.isBlank()) {
            return true;
        }

        String title = todo.getTitle() == null ? "" : todo.getTitle().toLowerCase(Locale.ROOT);
        String description = todo.getDescription() == null ? "" : todo.getDescription().toLowerCase(Locale.ROOT);
        String category = todo.getCategory() == null ? "" : todo.getCategory().toLowerCase(Locale.ROOT);
        return title.contains(search) || description.contains(search) || category.contains(search);
    }

    private boolean matchesStatus(Todo todo, TodoStatus status) {
        return status == null || todo.getStatus() == status;
    }

    private boolean matchesPriority(Todo todo, TodoPriority priority) {
        return priority == null || todo.getPriority() == priority;
    }

    private boolean matchesCategory(Todo todo, String category) {
        if (category.isBlank()) {
            return true;
        }
        String todoCategory = todo.getCategory() == null ? "" : todo.getCategory().toLowerCase(Locale.ROOT);
        return todoCategory.contains(category);
    }

    private boolean isPastTodo(Todo todo, LocalDate today) {
        boolean completed = todo.getStatus() == TodoStatus.COMPLETED;
        boolean overdue = todo.getStatus() != TodoStatus.COMPLETED && todo.getDueDate() != null && todo.getDueDate().isBefore(today);
        return completed || overdue;
    }

    private void validateRequest(TodoRequest request) {
        if (request == null) {
            throw new WebApplicationException("Request body is required.", 400);
        }
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new WebApplicationException("Title is required.", 400);
        }
    }

    private void applyRequest(Todo todo, TodoRequest request) {
        todo.setTitle(request.getTitle().trim());
        todo.setDescription(request.getDescription());
        todo.setPriority(request.getPriority() == null ? TodoPriority.MEDIUM : request.getPriority());
        TodoStatus nextStatus = request.getStatus() == null ? TodoStatus.PENDING : request.getStatus();
        todo.setStatus(nextStatus);
        todo.setCategory(request.getCategory());
        todo.setDueDate(request.getDueDate());

        if (nextStatus == TodoStatus.COMPLETED) {
            if (todo.getCompletedDate() == null) {
                todo.setCompletedDate(LocalDateTime.now());
            }
        } else {
            todo.setCompletedDate(null);
        }
    }

    private TodoResponse toResponse(Todo todo, LocalDate today) {
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setDescription(todo.getDescription());
        response.setPriority(todo.getPriority());
        response.setStatus(todo.getStatus());
        response.setCategory(todo.getCategory());
        response.setDueDate(todo.getDueDate());
        response.setCreatedDate(todo.getCreatedDate());
        response.setUpdatedDate(todo.getUpdatedDate());
        response.setCompletedDate(todo.getCompletedDate());
        response.setOverdue(todo.getStatus() != TodoStatus.COMPLETED && todo.getDueDate() != null && todo.getDueDate().isBefore(today));
        return response;
    }
}
