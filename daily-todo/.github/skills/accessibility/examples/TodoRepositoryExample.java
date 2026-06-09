package org.dailydots.examples.accessibility;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.dailydots.entity.Todo;

import java.util.List;

/**
 * Example repository mirroring the current TodoRepository style.
 */
@ApplicationScoped
public class TodoRepositoryExample implements PanacheRepository<Todo> {

    /**
     * Lists TODO items with deterministic sorting.
     */
    public List<Todo> listSorted(String sortBy, String sortDir) {
        return findAll(resolveSort(sortBy, sortDir)).list();
    }

    /**
     * Optional helper showing a focused repository method.
     */
    public boolean existsByTitle(String title) {
        if (title == null || title.isBlank()) {
            return false;
        }
        return count("lower(title)", title.trim().toLowerCase()) > 0;
    }

    private Sort resolveSort(String sortBy, String sortDir) {
        String normalizedSortBy = sortBy == null ? "createdDate" : sortBy;
        String field = switch (normalizedSortBy) {
            case "priority" -> "priority";
            case "status" -> "status";
            case "dueDate" -> "dueDate";
            case "createdDate", "creationDate" -> "createdDate";
            default -> "createdDate";
        };

        Sort.Direction direction = "asc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.Ascending
                : Sort.Direction.Descending;

        return Sort.by(field, direction).and("id", Sort.Direction.Descending);
    }
}
