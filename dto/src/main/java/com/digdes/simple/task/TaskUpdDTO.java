package com.digdes.simple.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Задача для обновления")
public class TaskUpdDTO {
    @Schema (description = "id задачи. Обязательное поле")
    Long id;
    @Schema (description = "Код проекта. Обязательное поле")
    String code;
    @Schema (description = "Наименование задачи. Обязательное поле")
    String name;
    @Schema (description = "Описание задачи. Необязательное поле")
    String details;
    @Schema (description = "id исполнителя задачи. Необязательное поле")
    Long employee;
    @Schema (description = "Трудозатраты. Обязательное поле")
    int laborCost;
    @Schema (description = "Крайний срок исполнения. Обязательное поле")
    LocalDate executionDate;
}
