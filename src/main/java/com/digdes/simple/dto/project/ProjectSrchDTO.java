package com.digdes.simple.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Поиск проектов")
public class ProjectSrchDTO {
    @Schema(description = "Код проекта")
    private String code;
    @Schema(description = "Наименование")
    private String name;
    @Schema(description = "Статус проекта")
    private String status;
}
