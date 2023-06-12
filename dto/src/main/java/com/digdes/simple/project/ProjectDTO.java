package com.digdes.simple.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Проект")
public class ProjectDTO {
    @Schema(description = "Код проекта")
    private String code;
    @Schema(description = "Наименование")
    private String name;
    @Schema(description = "Описание.")
    private String description;
    @Schema(description = "Статус проекта")
    private String status;
}
