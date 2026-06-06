package org.dailydots.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dailydots.dto.TodoRequest;
import org.dailydots.dto.TodoResponse;
import org.dailydots.dto.TodoSummaryResponse;
import org.dailydots.entity.TodoPriority;
import org.dailydots.entity.TodoStatus;
import org.dailydots.service.TodoService;

import java.net.URI;
import java.util.List;

/**
 * REST resource exposing TODO operations.
 */
@Path("/api/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Lists TODO items with search, filtering and sorting.
     */
    @GET
    public List<TodoResponse> list(
            @QueryParam("search") String search,
            @QueryParam("status") TodoStatus status,
            @QueryParam("priority") TodoPriority priority,
            @QueryParam("category") String category,
            @DefaultValue("true") @QueryParam("includeCompleted") boolean includeCompleted,
            @DefaultValue("false") @QueryParam("showPastOnly") boolean showPastOnly,
            @DefaultValue("createdDate") @QueryParam("sortBy") String sortBy,
            @DefaultValue("desc") @QueryParam("sortDir") String sortDir
    ) {
        return todoService.listTodos(search, status, priority, category, includeCompleted, showPastOnly, sortBy, sortDir);
    }

    /**
     * Returns summary counts for dashboard display.
     */
    @GET
    @Path("/summary")
    public TodoSummaryResponse summary() {
        return todoService.summary();
    }

    /**
     * Returns completed and overdue TODO history.
     */
    @GET
    @Path("/history")
    public List<TodoResponse> history() {
        return todoService.getHistory();
    }

    /**
     * Returns one TODO item by id.
     */
    @GET
    @Path("/{id}")
    public TodoResponse get(@PathParam("id") Long id) {
        return todoService.getById(id);
    }

    /**
     * Creates a TODO item.
     */
    @POST
    public Response create(TodoRequest request) {
        TodoResponse created = todoService.create(request);
        return Response.created(URI.create("/api/todos/" + created.getId())).entity(created).build();
    }

    /**
     * Updates a TODO item.
     */
    @PUT
    @Path("/{id}")
    public TodoResponse update(@PathParam("id") Long id, TodoRequest request) {
        return todoService.update(id, request);
    }

    /**
     * Updates status for a TODO item.
     */
    @PATCH
    @Path("/{id}/status")
    public TodoResponse updateStatus(@PathParam("id") Long id, @QueryParam("status") TodoStatus status) {
        return todoService.updateStatus(id, status);
    }

    /**
     * Deletes a TODO item.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        todoService.delete(id);
        return Response.noContent().build();
    }
}
