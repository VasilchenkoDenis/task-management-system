package com.denisvasilchenko.tms.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @Schema(example = "1")
    private Long id;

    @Size(min = 4, max = 255)
    @Schema(example = "Example username")
    private String username;

    @Size(min = 5, max = 255)
    @Schema(example = "user@gmail.com")
    private String email;
}
