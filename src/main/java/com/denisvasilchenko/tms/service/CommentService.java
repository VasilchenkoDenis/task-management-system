package com.denisvasilchenko.tms.service;

import com.denisvasilchenko.tms.dto.comment.CommentRequest;
import com.denisvasilchenko.tms.model.Comment;
import com.denisvasilchenko.tms.model.Task;
import com.denisvasilchenko.tms.model.User;
import com.denisvasilchenko.tms.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    public Comment createComment(CommentRequest commentRequest, User author/*, Long taskId*/) {
        validateText(commentRequest);
//        Task task = taskService.findById(taskId);
            return Comment.builder()
                    .text(commentRequest.getText())
                    .author(author)
//                    .task(task)
                    .createdAt(LocalDateTime.now())
                    .build();


    }

    public Comment addComment(CommentRequest commentRequest, User author, Long taskId) {
        Task task = taskService.findById(taskId);
        if(userService.hasAccess(author, task)) {
        Comment comment = Comment.builder()
                .text(commentRequest.getText())
                .author(author)
//                .task(task)
                .createdAt(LocalDateTime.now())
                .build();
        List<Comment> comments = task.getComments();
        comments.add(comment);
        task.setComments(comments);
        taskService.save(task);
        return comment;}
        else{throw new AccessDeniedException("The user does not have administrator rights, or is not assignee");}
    }

    private void validateText (CommentRequest commentRequest) {
        if(commentRequest.getText() == null || commentRequest.getText().trim().equals("")) {
            throw new IllegalArgumentException("Comment text cannot be empty");
        }
    }

    private void validateAuthor (User author, Task task) {
        userService.hasAccess(author, task);
    }

}
