package com.digdes.simple.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Поиск сотрудников")
public class EmployeeSrchDTO {
    String uid;
    @Schema(description = "Имя")
    String firstname;
    @Schema (description = "Фамилия")
    String lastname;
    @Schema (description = "Отчетсво")
    String surname;
    @Schema (description = "Логин")
    String account;
    @Schema (description = "e-mail")
    String email;
}
