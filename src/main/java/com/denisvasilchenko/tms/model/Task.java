package com.denisvasilchenko.tms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the task", example = "1")
    private long id;
    @Column(nullable = false)
    @Schema(description = "Title of the task", example = "Complete the project report")
    private String title;
    @Schema(description = "Detailed description of the task", example = "The report should cover the project milestones and outcomes.")
    @Size(max = 255, message = "Length no more than 255 characters")
    private String description;
    @Enumerated(EnumType.STRING)
    @Schema(description = "Current status of the task", example = "IN_PROGRESS")
    private TaskStatus taskStatus;
    @Enumerated(EnumType.STRING)
    @Schema(description = "Priority level of the task", example = "HIGH")
    private Priority priority;
    @ManyToOne
    @Schema(description = "User  who created the task")
    private User author;
    @ManyToOne
    @Schema(description = "User  assigned to the task")
    private User assignee;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    @Schema(description = "List of comments associated with the task")
    private List<Comment> comments = new ArrayList<>();

}
