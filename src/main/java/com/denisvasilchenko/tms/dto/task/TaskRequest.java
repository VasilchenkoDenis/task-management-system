package com.denisvasilchenko.tms.dto.task;

import com.denisvasilchenko.tms.model.Comment;
import com.denisvasilchenko.tms.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    private long id;
    @NotBlank (message = "Title must not be empty")
    @NotNull (message = "Title must not be empty")
    private String title;
    private String description;
    private String taskStatus;
    private String priority;
    private User assignee;
    private User author;
    private List<Comment> comments;
}
