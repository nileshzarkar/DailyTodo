package org.dailydots.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.dailydots.entity.Todo;

import java.util.List;

/**
 * Data persistence operations for TODO entities.
 */
@ApplicationScoped
public class TodoRepository implements PanacheRepository<Todo> {

    /**
     * Returns all TODO items sorted by supported fields.
     *
     * @param sortBy field name used for ordering
     * @param sortDir asc or desc
     * @return sorted TODO items
     */
    public List<Todo> listSorted(String sortBy, String sortDir) {
        return findAll(resolveSort(sortBy, sortDir)).list();
    }

    private Sort resolveSort(String sortBy, String sortDir) {
        String normalizedSortBy = sortBy == null ? "createdDate" : sortBy;
        String field = switch (normalizedSortBy) {
            case "priority" -> "priority";
            case "status" -> "status";
            case "dueDate" -> "dueDate";
            case "creationDate", "createdDate" -> "createdDate";
            default -> "createdDate";
        };

        Sort.Direction direction = "asc".equalsIgnoreCase(sortDir) ? Sort.Direction.Ascending : Sort.Direction.Descending;
        return Sort.by(field, direction).and("id", Sort.Direction.Descending);
    }
}
