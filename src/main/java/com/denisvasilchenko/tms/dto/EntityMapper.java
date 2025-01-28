package com.denisvasilchenko.tms.dto;

import com.denisvasilchenko.tms.dto.comment.CommentResponse;
import com.denisvasilchenko.tms.dto.task.TaskRequest;
import com.denisvasilchenko.tms.dto.task.TaskResponse;
import com.denisvasilchenko.tms.dto.user.UserResponse;
import com.denisvasilchenko.tms.model.*;
import com.denisvasilchenko.tms.service.UserService;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {



    TaskResponse taskToResponse(Task task);
    UserResponse userToResponse (User user);
    List<CommentResponse> commentToCommentResponse (List<Comment> comments);
    CommentResponse commentToCommentResponse1 (Comment comment);

    @Mapping(target = "taskStatus", source = "taskRequest.taskStatus")
    @Mapping(target = "priority", source = "taskRequest.priority")
    @Mapping(target = "comments", source = "taskRequest.comments")
    Task requestToTask(TaskRequest taskRequest, @Context UserService userService);

    default TaskStatus mapTaskStatus(String taskStatus) {
        return taskStatus == null ? TaskStatus.PENDING : TaskStatus.valueOf(taskStatus.toUpperCase());
    }

    default Priority mapPriority(String priority) {
        return priority == null ? Priority.LOW : Priority.valueOf(priority.toUpperCase());
    }

    default List<Comment> mapComments(List<Comment> comments, @Context UserService userService) {
        List<Comment> comments1 = comments == null ? new ArrayList<>() : comments;
        if (!comments1.isEmpty()) {
            for (Comment comment : comments1) {
                comment.setCreatedAt(LocalDateTime.now());
                comment.setAuthor(userService.getCurrentUser());
            }
        }
        return comments1;
    }






}
