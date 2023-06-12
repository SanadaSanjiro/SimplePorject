package com.digdes.simple.test.mapping.employee;

import com.digdes.simple.employee.EmployeeCrtDTO;
import com.digdes.simple.employee.EmployeeCrtMapper;
import com.digdes.simple.employee.EmployeeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeCrtMapperTest {

    @Test
    @DisplayName("EmployeeCrtMapper maps all fields")
    void employeeCrtMapper_MapDTOtoModel() {
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
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
        dto.setPassword(password);
        EmployeeModel em = EmployeeCrtMapper.map(dto);
        Assertions.assertEquals(firstname, em.getFirstName());
        Assertions.assertEquals(lastname, em.getLastName());
        Assertions.assertEquals(surname, em.getSurName());
        Assertions.assertEquals(position, em.getPosition());
        Assertions.assertEquals(account, em.getAccount());
        Assertions.assertEquals(email, em.getEMail());
        Assertions.assertEquals(password, em.getPassword());
    }
}