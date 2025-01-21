package com.denisvasilchenko.tms.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private String comment;
    @ManyToOne
    private User author;
    @ManyToOne
    private User assignee;

}
