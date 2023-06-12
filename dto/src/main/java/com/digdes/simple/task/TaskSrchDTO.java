package com.digdes.simple.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Задача")
public class TaskSrchDTO {
    @Schema (description = "Наименование задачи")
    String name;
    @Schema (description = "id исполнителя задачи")
    Long employee;
    @Schema (description = "Крайний срок исполнения")
    LocalDate executionDate;
    @Schema (description = "Статус задачи")
    String status;
    @Schema (description = "id автора задачи")
    Long author;
    @Schema (description = "Дата создания")
    LocalDate creationDate;
}
