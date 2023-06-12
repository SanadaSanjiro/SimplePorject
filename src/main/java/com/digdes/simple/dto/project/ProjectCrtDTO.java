package com.digdes.simple.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Новый проект/изменение проекта")
public class ProjectCrtDTO {
    @Schema(description = "Код проекта. Обязательное поле.")
    private String code;
    @Schema(description = "Наименование. Обязательное поле.")
    private String name;
    @Schema(description = "Описание. Необязательное поле.")
    private String description;
}
