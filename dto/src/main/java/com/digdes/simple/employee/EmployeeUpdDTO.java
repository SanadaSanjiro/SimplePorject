package com.digdes.simple.employee;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Редактируемый сотрудник")
public class EmployeeUpdDTO {
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
}
