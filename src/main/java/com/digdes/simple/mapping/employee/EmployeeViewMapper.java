package com.digdes.simple.mapping.employee;

import com.digdes.simple.dto.employee.EmployeeViewDTO;
import com.digdes.simple.model.employee.EmployeeModel;
import org.springframework.util.ObjectUtils;

//Класс для преобразования модели в ДТО для возврата в качестве результатов запроса
//Передает все поля модели за исключением пароля

public class EmployeeViewMapper {
    public static EmployeeViewDTO map (EmployeeModel model) {
        EmployeeViewDTO dto = new EmployeeViewDTO();
        if (!ObjectUtils.isEmpty(model.getId())) {
            dto.setId(model.getId());
        }
        if (!ObjectUtils.isEmpty(model.getFirstName())) {
            dto.setFirstname(model.getFirstName());
        }
        if (!ObjectUtils.isEmpty(model.getLastName())) {
            dto.setLastname(model.getLastName());
        }
        if (!ObjectUtils.isEmpty(model.getSurName())) {
            dto.setSurname(model.getSurName());
        }
        if (!ObjectUtils.isEmpty(model.getPosition())) {
            dto.setPosition(model.getPosition());
        }
        if (!ObjectUtils.isEmpty(model.getAccount())) {
            dto.setAccount(model.getAccount());
        }
        if (!ObjectUtils.isEmpty(model.getEMail())) {
            dto.setEmail(model.getEMail());
        }
        if (!ObjectUtils.isEmpty(model.getStatus())) {
            switch (model.getStatus().name()) {
                case ("ACTIVE") :
                    dto.setStatus("ACTIVE");
                    break;
                case ("DELETED"):
                    dto.setStatus("DELETED");
                    break;
            }
        }
        return dto;
    }
}
