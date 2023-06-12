package com.digdes.simple.mapping.employee;

import com.digdes.simple.dto.employee.EmployeeUpdDTO;
import com.digdes.simple.model.employee.EmployeeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeUpdMapperTest {

    @Test
    @DisplayName("EmployeeUpdMapper maps all fields")
    void employeeUpdMapper_MapDTOtoModel() {
        EmployeeUpdDTO dto = new EmployeeUpdDTO();
        final String firstname = "FirstName";
        final String lastname = "LastName";
        final String surname = "surname";
        final String position = "position";
        final String account = "account";
        final String email = "email";
        final String password = "password";
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        dto.setSurname(surname);
        dto.setPosition(position);
        dto.setAccount(account);
        dto.setEmail(email);
        EmployeeModel em = EmployeeUpdMapper.map(dto);
        Assertions.assertEquals(firstname, em.getFirstName());
        Assertions.assertEquals(lastname, em.getLastName());
        Assertions.assertEquals(surname, em.getSurName());
        Assertions.assertEquals(position, em.getPosition());
        Assertions.assertEquals(account, em.getAccount());
        Assertions.assertEquals(email, em.getEMail());
    }
}