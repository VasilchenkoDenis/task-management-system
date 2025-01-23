package com.denisvasilchenko.tms.dto.comment;

import com.denisvasilchenko.tms.dto.user.UserResponse;
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
public class CommentResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Example comment text")
    private String text;

    private UserResponse author;

    private LocalDateTime createdAt;
}
