package com.denisvasilchenko.tms.controller;
import com.denisvasilchenko.tms.dto.EntityMapper;
import com.denisvasilchenko.tms.dto.task.TaskRequest;
import com.denisvasilchenko.tms.dto.task.TaskResponse;
import com.denisvasilchenko.tms.model.Priority;
import com.denisvasilchenko.tms.model.Task;
import com.denisvasilchenko.tms.model.TaskStatus;
import com.denisvasilchenko.tms.repository.TaskSpecification;
import com.denisvasilchenko.tms.service.TaskService;
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


    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<TaskResponse>> getTasks(@RequestParam(required = false) String authorUsername,
                                                      @RequestParam(required = false) String assigneeUsername,
                                                      @RequestParam(required = false) String title,
                                                      @RequestParam(required = false) TaskStatus status,
                                                      @RequestParam(required = false) Priority priority,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Specification<Task> taskSpecification = TaskSpecification.
                filterByCriteria(authorUsername,
                        assigneeUsername, title,
                        status, priority);

        Page<TaskResponse> tasks = taskService.findAll(taskSpecification, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask (@PathVariable @Min(1) Long id){
        TaskResponse taskResponse = taskService.findById(id);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse response = entityMapper.entityToResponse(taskService.save(taskRequest));
            return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> deleteTask (@PathVariable @Min(1) Long id) {
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')") /*проверить так как а момент написания, не было комментов и ответственных*/
    public ResponseEntity<TaskResponse> updateTask (@Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse response = entityMapper.entityToResponse(taskService.update(taskRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
