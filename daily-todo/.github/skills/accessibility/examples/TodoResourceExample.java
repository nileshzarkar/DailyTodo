package org.dailydots.examples.accessibility;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dailydots.dto.TodoRequest;
import org.dailydots.dto.TodoResponse;

import java.net.URI;
import java.util.Optional;

/**
 * Example resource showing layered flow:
 * Resource -> Service -> Repository.
 */
@Path("/api/example/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResourceExample {

    private final TodoServiceExample todoService;

    public TodoResourceExample(TodoServiceExample todoService) {
        this.todoService = todoService;
    }

    /**
     * Example create endpoint returning field-aware validation payloads.
     */
    @POST
    public Response create(TodoRequest request) {
        Optional<TodoValidationErrorResponseExample> validation = todoService.validateForCreate(request);
        if (validation.isPresent()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(validation.get())
                    .build();
        }

        TodoResponse created = todoService.create(request);
        return Response.created(URI.create("/api/example/todos/" + created.getId()))
                .entity(created)
                .build();
    }
}
