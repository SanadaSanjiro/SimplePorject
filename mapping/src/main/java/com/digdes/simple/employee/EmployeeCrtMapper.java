package com.digdes.simple.employee;

import org.springframework.util.ObjectUtils;


//Класс для преобразования в модель ДТО при создании сотрудника
public class EmployeeCrtMapper {
    public static EmployeeModel map (EmployeeCrtDTO dto) {

        EmployeeModel model = new EmployeeModel();
        //model.setUid(someUIDGenerator);

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
        return model;
    }
}
