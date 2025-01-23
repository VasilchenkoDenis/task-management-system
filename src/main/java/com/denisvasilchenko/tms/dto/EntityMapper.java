package com.denisvasilchenko.tms.dto;


import com.denisvasilchenko.tms.dto.comment.CommentResponse;
import com.denisvasilchenko.tms.dto.task.TaskRequest;
import com.denisvasilchenko.tms.dto.task.TaskResponse;
import com.denisvasilchenko.tms.dto.user.UserResponse;
import com.denisvasilchenko.tms.model.Comment;
import com.denisvasilchenko.tms.model.Task;
import com.denisvasilchenko.tms.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring")
public interface EntityMapper {
    TaskResponse entityToResponse (Task task);
    Task requestToEntity (TaskRequest taskRequest);
    UserResponse userToResponse (User user);
    List<CommentResponse> commentToCommentResponse (List<Comment> comments);

}
