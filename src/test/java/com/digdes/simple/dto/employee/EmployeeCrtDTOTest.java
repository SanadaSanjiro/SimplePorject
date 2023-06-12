package com.digdes.simple.dto.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeCrtDTOTest {
    @Test
    @DisplayName("EmployeeCrtDTO setters and getters are ok")
    public void employeeCrtDTO_CheckSettersGetters() {
        final String firstname = "FirstName";
        final String lastname = "LastName";
        final String surname = "surname";
        final String position = "position";
        final String account = "account";
        final String email = "email";
        final String password = "password";
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        dto.setSurname(surname);
        dto.setPosition(position);
        dto.setAccount(account);
        dto.setEmail(email);
        dto.setPassword(password);
        Assertions.assertEquals(firstname, dto.getFirstname());
        Assertions.assertEquals(lastname, dto.getLastname());
        Assertions.assertEquals(surname, dto.getSurname());
        Assertions.assertEquals(position, dto.getPosition());
        Assertions.assertEquals(account, dto.getAccount());
        Assertions.assertEquals(email, dto.getEmail());
        Assertions.assertEquals(password, dto.getPassword());
    }
}