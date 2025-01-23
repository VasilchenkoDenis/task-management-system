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
@Schema(name = "Task Response")
public class TaskResponse {
    private long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private Priority priority;
    private UserResponse author;
    private UserResponse assignee;
    private List<CommentResponse> comments = new ArrayList<>();
}
