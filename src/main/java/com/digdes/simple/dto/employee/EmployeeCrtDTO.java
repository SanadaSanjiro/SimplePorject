package com.digdes.simple.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// DTO для нового сотрудника
@Data
@Schema (description = "Новый сотрудник")
public class EmployeeCrtDTO {
    @Schema(description = "Имя. Обязательное поле")
    String firstname;
    @Schema (description = "Фамилия. Обязательное поле")
    String lastname;
    @Schema (description = "Отчетсво. Необязательное поле")
    String surname;
    @Schema (description = "Должность. Необязательное поле")
    String position;
    @Schema (description = "Логин. Необязательное поле")
    String account;
    @Schema (description = "e-mail. Необязательное поле")
    String email;
    @Schema (description = "Пароль. Необязательное поле")
    String password;
}
