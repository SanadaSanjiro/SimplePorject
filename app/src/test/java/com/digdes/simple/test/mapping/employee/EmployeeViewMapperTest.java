package com.digdes.simple.test.mapping.employee;

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.employee.EmployeeViewDTO;
import com.digdes.simple.employee.EmployeeViewMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeViewMapperTest {

    @Test
    @DisplayName("EmployeeViewMapper maps all fields")
    void employeeViewMappper_MapModelToDTO() {
        final Long id = 1L;
        final String firstname = "FirstName";
        final String lastname = "LastName";
        final String surname = "surname";
        final String position = "position";
        final String account = "account";
        final String email = "email";
        final EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        EmployeeModel em = new EmployeeModel();
        em.setId(id);
        em.setFirstName(firstname);
        em.setLastName(lastname);
        em.setSurName(surname);
        em.setPosition(position);
        em.setAccount(account);
        em.setEMail(email);
        em.setStatus(employeeStatus);
        EmployeeViewDTO dto = EmployeeViewMapper.map(em);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(firstname, dto.getFirstname());
        Assertions.assertEquals(lastname, dto.getLastname());
        Assertions.assertEquals(surname, dto.getSurname());
        Assertions.assertEquals(position, dto.getPosition());
        Assertions.assertEquals(account, dto.getAccount());
        Assertions.assertEquals(email, dto.getEmail());
        Assertions.assertEquals(employeeStatus.toString(), dto.getStatus());
    }
}