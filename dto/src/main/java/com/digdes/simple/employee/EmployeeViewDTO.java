package com.digdes.simple.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//DTO для отображения данных по сотруднику
@Data
@Schema (description = "Отображаемые данные сотрудника")
public class EmployeeViewDTO {
    @Schema (description = "Уникальный id. Присваивается системой.")
    Long id;
    @Schema (description = "Имя")
    String firstname;
    @Schema (description = "Фамилия")
    String lastname;
    @Schema (description = "Отчетсво")
    String surname;
    @Schema (description = "Должность")
    String position;
    @Schema (description = "Логин")
    String account;
    @Schema (description = "e-mail")
    String email;
    @Schema (description = "Статус")
    String status;
}
