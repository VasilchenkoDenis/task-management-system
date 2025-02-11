package com.denisvasilchenko.tms.dto.comment;

import com.denisvasilchenko.tms.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {
    private long id;
    @Schema(example = "Example comment text")
    private String text;
    private User author;
    private LocalDateTime createdAt;
}
