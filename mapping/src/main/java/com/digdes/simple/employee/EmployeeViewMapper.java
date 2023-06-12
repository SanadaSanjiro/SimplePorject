package com.digdes.simple.employee;

import org.springframework.util.ObjectUtils;

//Класс для преобразования модели в ДТО для
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
            dto.setStatus(model.getStatus().toString());
        }
        return dto;
    }
}
