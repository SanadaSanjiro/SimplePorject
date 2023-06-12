package com.digdes.simple.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "Проект")
public class ProjectDTO {
    private String code;
    private String uuid;
    private String name;
    private String description;
    private String status;
}
