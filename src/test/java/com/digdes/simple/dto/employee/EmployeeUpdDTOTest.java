package com.digdes.simple.dto.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeUpdDTOTest {
    @Test
    @DisplayName("EmployeeUpdDTO setters and getters are ok")
    public void employeeUpdDTO_CheckSettersGetters() {
        final Long id = 1L;
        final String firstname = "FirstName";
        final String lastname = "LastName";
        final String surname = "surname";
        final String position = "position";
        final String account = "account";
        final String email = "email";
        EmployeeUpdDTO dto = new EmployeeUpdDTO();
        dto.setId(id);
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        dto.setSurname(surname);
        dto.setPosition(position);
        dto.setAccount(account);
        dto.setEmail(email);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(firstname, dto.getFirstname());
        Assertions.assertEquals(lastname, dto.getLastname());
        Assertions.assertEquals(surname, dto.getSurname());
        Assertions.assertEquals(position, dto.getPosition());
        Assertions.assertEquals(account, dto.getAccount());
        Assertions.assertEquals(email, dto.getEmail());
    }
}