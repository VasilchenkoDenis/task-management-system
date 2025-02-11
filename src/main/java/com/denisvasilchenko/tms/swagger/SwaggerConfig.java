package com.denisvasilchenko.tms.swagger;

import com.denisvasilchenko.tms.dto.comment.CommentResponse;
import com.denisvasilchenko.tms.dto.task.TaskResponse;
import com.denisvasilchenko.tms.dto.user.UserResponse;
import com.denisvasilchenko.tms.model.Priority;
import com.denisvasilchenko.tms.model.TaskStatus;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearer Auth")
                .description("JWT auth")
                .scheme("bearer")
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);

        return new OpenAPI()
                .info(new Info()
                        .title("Swagger API").version("1.0.0").description("jwt token for demo user: "))
                .security(List.of(new SecurityRequirement().addList(securityScheme.getName())))
                .components(new Components()
                        .addSecuritySchemes(securityScheme.getName(), securityScheme)
                        .addSchemas("idSchema", new Schema<Map<String, Object>>()
                                .addProperty("id", new IntegerSchema().format("int64").example(1)))
                        .addSchemas("usernameSchema", new Schema<Map<String, Object>>()
                                .addProperty("username", new StringSchema().example("Example username")))
                        .addSchemas("emailSchema", new Schema<Map<String, Object>>()
                                .addProperty("email", new StringSchema().example("user@mail.example")))
                        .addSchemas("titleSchema", new Schema<Map<String, Object>>()
                                .addProperty("title", new StringSchema().example("Example task title")))
                        .addSchemas("descriptionSchema", new Schema<Map<String, Object>>()
                                .addProperty("description", new StringSchema().example("Example task description")))
                        .addSchemas("statusSchema", new Schema<Map<String, Object>>()
                                .addProperty("taskStatus", new StringSchema()._enum(Arrays.stream(TaskStatus.values())
                                        .map(Enum::name).collect(Collectors.toList()))
                                        .example(TaskStatus.IN_PROGRESS)))
                        .addSchemas("prioritySchema", new Schema<Map<String, Object>>()
                                .addProperty("priority", new StringSchema()._enum(Arrays.stream(Priority.values())
                                        .map(Enum::name).collect(Collectors.toList()))
                                        .example(Priority.HIGH.name())))
                        .addSchemas("authorSchema", new Schema<>()
                                .addProperty("author", new Schema<>()
                                        .example(new UserResponse(1L, "Example name", "user@mail.example"))))
                        .addSchemas("assigneeSchema", new Schema<>()
                                .addProperty("assignee", new Schema<>().example(
                                        new UserResponse(1L, "Example name", "user@mail.example"))))
                        .addSchemas("commentsSchema", new Schema<Map<String, Object>>()
                                .addProperty("comments", new Schema<Map<String, Object>>().example(List.of(
                                        new CommentResponse(1L, "Example comment text 1",
                                                new UserResponse(1L, "Example username",
                                                        "user@mail.example"), LocalDateTime.now()),
                                        new CommentResponse(2L, "Example comment text 2"
                                                , new UserResponse(2L, "Example username2",
                                                "user2@mail.example"), LocalDateTime.now())
                                ))))
                        .addSchemas("taskResponseSchema", new Schema<TaskResponse>()._default(
                                new TaskResponse(1L, "Example title text", "Example description text",
                                        TaskStatus.PENDING, Priority.HIGH,
                                        new UserResponse(1L, "Example username", "user@mail.example"),
                                        new UserResponse(2L, "Example username2", "user2@mail.example")
                                        , List.of(
                                        new CommentResponse(1L, "Example comment text",
                                                new UserResponse(1L, "Example username", "user@mail.example")
                                                , LocalDateTime.now()),
                                        new CommentResponse(2L, "Example comment text 2",
                                                new UserResponse(2L, "Example username2", "user2@mail.example")
                                                , LocalDateTime.now())
                                )
                                ))));

    }
}
