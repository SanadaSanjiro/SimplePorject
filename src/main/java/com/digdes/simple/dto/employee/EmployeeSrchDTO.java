package com.digdes.simple.dto.employee;

import com.digdes.simple.model.employee.EmployeeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema (description = "Поиск сотрудников")
public class EmployeeSrchDTO {
    String firstname;
    @Schema (description = "Фамилия")
    String lastname;
    @Schema (description = "Отчетсво")
    String surname;
    @Schema (description = "Логин")
    String account;
    @Schema (description = "e-mail")
    String email;
    @Schema (description = "Статус. Поиск производится только по сотрудникам в статусе Активный")
    final String status = EmployeeStatus.ACTIVE.toString();
}
