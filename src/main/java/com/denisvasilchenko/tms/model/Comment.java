package com.denisvasilchenko.tms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String text;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + text + '\'' +
                ", author=" + author +
                ", createdAt=" + createdAt +
                '}';
    }
}
