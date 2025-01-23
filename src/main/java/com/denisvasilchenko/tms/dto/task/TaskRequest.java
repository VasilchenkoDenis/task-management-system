package com.denisvasilchenko.tms.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank (message = "Title must not be empty")
    @NotNull (message = "Title must not be empty")
    private String title;
    private String description;
    private String taskStatus;
    private String priority;
    private String assigneeEmail;
    private Long assigneeId;
}
