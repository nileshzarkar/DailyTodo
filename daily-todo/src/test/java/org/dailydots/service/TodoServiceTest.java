package org.dailydots.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.dailydots.dto.TodoRequest;
import org.dailydots.dto.TodoResponse;
import org.dailydots.entity.Todo;
import org.dailydots.entity.TodoPriority;
import org.dailydots.entity.TodoStatus;
import org.dailydots.repository.TodoRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class TodoServiceTest {

    @Inject
    TodoService todoService;

    @Inject
    TodoRepository todoRepository;

    @Test
    @TestTransaction
    void updateStatusShouldPreserveCompletedDateWhenAlreadyCompleted() {
        TodoResponse created = todoService.create(buildRequest("Preserve completion", TodoStatus.COMPLETED, LocalDate.now()));
        Todo persisted = todoRepository.findById(created.getId());
        LocalDateTime initialCompletedDate = persisted.getCompletedDate();

        TodoResponse updated = todoService.updateStatus(created.getId(), TodoStatus.COMPLETED);

        assertNotNull(initialCompletedDate);
        assertEquals(initialCompletedDate, updated.getCompletedDate());
    }

    @Test
    @TestTransaction
    void updateStatusShouldSetCompletedDateOnFirstCompletion() {
        TodoResponse created = todoService.create(buildRequest("First completion", TodoStatus.PENDING, LocalDate.now().plusDays(1)));

        TodoResponse updated = todoService.updateStatus(created.getId(), TodoStatus.COMPLETED);

        assertEquals(TodoStatus.COMPLETED, updated.getStatus());
        assertNotNull(updated.getCompletedDate());
    }

    @Test
    @TestTransaction
    void updateStatusShouldClearCompletedDateWhenMovingAwayFromCompleted() {
        TodoResponse created = todoService.create(buildRequest("Clear completion", TodoStatus.COMPLETED, LocalDate.now()));

        TodoResponse updated = todoService.updateStatus(created.getId(), TodoStatus.IN_PROGRESS);

        assertEquals(TodoStatus.IN_PROGRESS, updated.getStatus());
        assertNull(updated.getCompletedDate());
    }

    @Test
    @TestTransaction
    void listTodosShouldExcludeCompletedWhenRequested() {
        todoService.create(buildRequest("Pending item", TodoStatus.PENDING, LocalDate.now().plusDays(2)));
        todoService.create(buildRequest("Completed item", TodoStatus.COMPLETED, LocalDate.now()));

        List<TodoResponse> todos = todoService.listTodos(
                "",
                null,
                null,
                "",
                false,
                false,
                "createdDate",
                "desc"
        );

        assertFalse(todos.isEmpty());
        assertTrue(todos.stream().noneMatch(todo -> todo.getStatus() == TodoStatus.COMPLETED));
    }

    @Test
    @TestTransaction
    void historyShouldContainCompletedAndOverdueTodos() {
        todoService.create(buildRequest("Completed history", TodoStatus.COMPLETED, LocalDate.now()));
        todoService.create(buildRequest("Overdue history", TodoStatus.PENDING, LocalDate.now().minusDays(1)));
        todoService.create(buildRequest("Future todo", TodoStatus.PENDING, LocalDate.now().plusDays(3)));

        List<TodoResponse> history = todoService.getHistory();

        assertEquals(2, history.size());
        assertTrue(history.stream().anyMatch(todo -> todo.getStatus() == TodoStatus.COMPLETED));
        assertTrue(history.stream().anyMatch(TodoResponse::isOverdue));
    }

    private TodoRequest buildRequest(String title, TodoStatus status, LocalDate dueDate) {
        TodoRequest request = new TodoRequest();
        request.setTitle(title);
        request.setDescription("Test description");
        request.setPriority(TodoPriority.MEDIUM);
        request.setStatus(status);
        request.setCategory("test");
        request.setDueDate(dueDate);
        return request;
    }
}
