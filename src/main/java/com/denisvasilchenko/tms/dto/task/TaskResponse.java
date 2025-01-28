package com.denisvasilchenko.tms.dto.task;

import com.denisvasilchenko.tms.dto.comment.CommentResponse;
import com.denisvasilchenko.tms.dto.user.UserResponse;
import com.denisvasilchenko.tms.model.Priority;
import com.denisvasilchenko.tms.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Task Response", description = "Response object for task details")
public class TaskResponse {

    @Schema(description = "Unique identifier of the task", example = "1")
    private long id;

    @Schema(description = "Title of the task", example = "Fix bug")
    private String title;

    @Schema(description = "Detailed description of the task", example = "This task involves fixing a critical bug in the application.")
    private String description;

    @Schema(description = "Current status of the task", example = "IN_PROGRESS")
    private TaskStatus taskStatus;

    @Schema(description = "Priority level of the task", example = "HIGH")
    private Priority priority;

    @Schema(description = "Details of the user who created the task")
    private UserResponse author;

    @Schema(description = "Details of the user assigned to the task")
    private UserResponse assignee;

    @Schema(description = "List of comments associated with the task")
    private List<CommentResponse> comments = new ArrayList<>();
}
