package com.digdes.simple.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//DTO для отображения данных по сотруднику
@Data
@Schema (description = "Отображаемые данные сотрудника")
public class EmployeeViewDTO {
    Long id;
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
