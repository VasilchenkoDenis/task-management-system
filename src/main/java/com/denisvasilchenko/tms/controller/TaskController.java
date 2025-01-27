package com.denisvasilchenko.tms.controller;

import com.denisvasilchenko.tms.dto.EntityMapper;
import com.denisvasilchenko.tms.dto.task.TaskRequest;
import com.denisvasilchenko.tms.dto.task.TaskResponse;
import com.denisvasilchenko.tms.model.*;
import com.denisvasilchenko.tms.repository.TaskSpecification;
import com.denisvasilchenko.tms.service.CommentService;
import com.denisvasilchenko.tms.service.TaskService;
import com.denisvasilchenko.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Data
@RestController
@Validated
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get list of tasks",
            description = "Returns a list of tasks with the ability to filter by various parameters and pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task list received successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(ref = "#/components/schemas/taskResponseSchema")))),
            @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tasks not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    public ResponseEntity<Page<TaskResponse>> getTasks(
            @Parameter(description = "Username of the task author", example = "PetrovDA")
            @RequestParam(required = false) String authorUsername,
            @Parameter(description = "Email of the task author", example = "user@gmail.com")
            @RequestParam(required = false) String authorEmail,
            @Parameter(description = "Username assigned", example = "IvanovDV")
            @RequestParam(required = false) String assigneeUsername,
            @Parameter(description = "Email assigned", example = "user@gmail.com")
            @RequestParam(required = false) String assigneeEmail,
            @Parameter(description = "Task title", example = "Fix bug")
            @RequestParam(required = false) String title,
            @Parameter(description = "Task status", example = "IN_PROGRESS")
            @RequestParam(required = false) TaskStatus status,
            @Parameter(description = "Task priority", example = "HIGH")
            @RequestParam(required = false) Priority priority,
            @Parameter(description = "Page number for pagination", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of tasks per page for pagination", example = "10")
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Specification<Task> taskSpecification = TaskSpecification.
                filterByCriteria(authorUsername, authorEmail,
                        assigneeUsername, assigneeEmail, title,
                        status, priority);

        Page<TaskResponse> tasks = taskService.findAll(taskSpecification, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable @Min(1) Long id) {
        TaskResponse taskResponse = entityMapper.taskToResponse(taskService.findById(id));
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = entityMapper.requestToTask(taskRequest);
        TaskResponse response = entityMapper.taskToResponse(taskService.save(task));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable @Min(1) Long id) {
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskRequest taskRequest) {

        TaskResponse response = entityMapper.taskToResponse(taskService
                .update(entityMapper.requestToTask(taskRequest)));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
