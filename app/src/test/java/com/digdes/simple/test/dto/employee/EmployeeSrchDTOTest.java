package com.digdes.simple.test.dto.employee;

import com.digdes.simple.employee.EmployeeSrchDTO;
import com.digdes.simple.employee.EmployeeStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeSrchDTOTest {
    @Test
    @DisplayName("EmployeeSrchDTO setters and getters are ok")
    public void employeeSrchDTO_CheckSettersGetters() {
        final String firstname = "FirstName";
        final String lastname = "LastName";
        final String surname = "surname";
        final String account = "account";
        final String email = "email";
        EmployeeSrchDTO dto = new EmployeeSrchDTO();
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        dto.setSurname(surname);
        dto.setAccount(account);
        dto.setEmail(email);
        Assertions.assertEquals(firstname, dto.getFirstname());
        Assertions.assertEquals(lastname, dto.getLastname());
        Assertions.assertEquals(surname, dto.getSurname());
        Assertions.assertEquals(account, dto.getAccount());
        Assertions.assertEquals(email, dto.getEmail());
        Assertions.assertEquals(EmployeeStatus.ACTIVE.toString(), dto.getStatus());
    }
}