package com.digdes.simple.mapping.employee;

//Класс для преобразования ДТО в модель при редактировании данных сотрудников

import com.digdes.simple.dto.employee.EmployeeUpdDTO;
import com.digdes.simple.model.EmployeeModel;
import org.springframework.util.ObjectUtils;

public class EmployeeUpdMapper {
    public static EmployeeModel map (EmployeeUpdDTO dto) {
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
        return model;
    }
}
