package com.digdes.simple.dto.employee;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Редактируемый сотрудник")
public class EmployeeUpdDTO {
    Long id;
    String uid;
    @Schema(description = "Имя")
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
