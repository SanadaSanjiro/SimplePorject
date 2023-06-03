package com.digdes.simple.mapping.employee;

import com.digdes.simple.dto.employee.EmployeeDTO;
import com.digdes.simple.model.EmployeeModel;
import com.digdes.simple.model.EmployeeStatus;
import org.springframework.util.ObjectUtils;

// Образцовое преобразование модели в DTO и обратно с полным набором полей.

public class EmployeeMapper {

    public static EmployeeModel map (EmployeeDTO dto) {
        EmployeeModel model = new EmployeeModel();
        if (!ObjectUtils.isEmpty(dto.getId())) {
            model.setId(dto.getId());
        }
        if (!ObjectUtils.isEmpty(dto.getUid())) {
            model.setUid(dto.getUid());
        }
        if (!ObjectUtils.isEmpty(dto.getFirstname())) {
            model.setFirstName(dto.getFirstname());
        }
        if (!ObjectUtils.isEmpty(dto.getLastname())) {
            model.setLastName(dto.getLastname());
        }
        if (!ObjectUtils.isEmpty(dto.getSurname())) {
            model.setSurName(dto.getSurname());
        }
        if (!ObjectUtils.isEmpty(dto.getPosition())) {
            model.setPosition(dto.getPosition());
        }
        if (!ObjectUtils.isEmpty(dto.getAccount())) {
            model.setAccount(dto.getAccount());
        }
        if (!ObjectUtils.isEmpty(dto.getEmail())) {
            model.setEMail(dto.getEmail());
        }
        if (!ObjectUtils.isEmpty(dto.getPassword())) {
            model.setPassword(dto.getPassword());
        }
        if (!ObjectUtils.isEmpty(dto.getStatus())) {
            switch (dto.getStatus()) {
                case ("ACTIVE"):
                    model.setStatus(EmployeeStatus.ACTIVE);
                    break;
                case ("DELETED"):
                    model.setStatus(EmployeeStatus.DELETED);
                    break;
            }
        }
        return model;
    }

    public static EmployeeDTO map (EmployeeModel model) {
        EmployeeDTO dto = new EmployeeDTO();
        if (!ObjectUtils.isEmpty(model.getId())) {
            dto.setId(model.getId());
        }
        if (!ObjectUtils.isEmpty(model.getUid())) {
            dto.setUid(model.getUid());
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
        if (!ObjectUtils.isEmpty(model.getPassword())) {
            dto.setPassword(model.getPassword());
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
