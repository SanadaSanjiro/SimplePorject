package com.digdes.simple.dto.employee;

import com.digdes.simple.model.employee.EmployeeStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeViewDTOTest {
    @Test
    @DisplayName("EmployeeViewDTO setters and getters are ok")
    public void employeeViewDTO_CheckSettersGetters() {
        final Long id = 1L;
        final String firstname = "FirstName";
        final String lastname = "LastName";
        final String surname = "surname";
        final String position = "position";
        final String account = "account";
        final String email = "email";
        final String status = EmployeeStatus.ACTIVE.toString();
        EmployeeViewDTO dto = new EmployeeViewDTO();
        dto.setId(id);
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        dto.setSurname(surname);
        dto.setPosition(position);
        dto.setAccount(account);
        dto.setEmail(email);
        dto.setStatus(status);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(firstname, dto.getFirstname());
        Assertions.assertEquals(lastname, dto.getLastname());
        Assertions.assertEquals(surname, dto.getSurname());
        Assertions.assertEquals(position, dto.getPosition());
        Assertions.assertEquals(account, dto.getAccount());
        Assertions.assertEquals(email, dto.getEmail());
        Assertions.assertEquals(status, dto.getStatus());
    }
}