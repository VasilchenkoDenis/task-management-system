package com.denisvasilchenko.tms.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatuses;
    @ElementCollection(targetClass = Priority.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "task_priority", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "priority")
    private Set<Priority> priority;
    private String comment;
    @ManyToOne
    private User author;
    @ManyToOne
    private User assignee;

}
