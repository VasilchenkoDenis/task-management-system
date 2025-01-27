package com.denisvasilchenko.tms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
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
