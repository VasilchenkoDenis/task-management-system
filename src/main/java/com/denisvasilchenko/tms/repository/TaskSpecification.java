package com.denisvasilchenko.tms.repository;

import com.denisvasilchenko.tms.model.Priority;
import com.denisvasilchenko.tms.model.Task;
import com.denisvasilchenko.tms.model.TaskStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    public static Specification<Task> filterByCriteria(String authorUsername,
                                                           String assigneeUsername,
                                                           String title, TaskStatus status,
                                                           Priority priority) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (authorUsername != null && !authorUsername.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("author").get("username"), authorUsername));
            }
            if (assigneeUsername != null && !assigneeUsername.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("assignee").get("username"), assigneeUsername));
            }
            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (priority != null) {
                predicates.add(criteriaBuilder.equal(root.get("priority"), priority));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
