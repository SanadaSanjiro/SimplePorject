package com.digdes.simple.model.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EmployeeModelTest {

    @Test
    @DisplayName("EmployeeModel setters and getters are ok")
    public void EmployeeModel_CheckSettersGetters() {
        final Long id = 1L;
        final String firstname = "FirstName";
        final String lastname = "LastName";
        final String surname = "surname";
        final String position = "position";
        final String account = "account";
        final String email = "email";
        final String password = "password";
        final EmployeeStatus status = EmployeeStatus.ACTIVE;
        EmployeeModel em = new EmployeeModel();
        em.setId(id);
        em.setFirstName(firstname);
        em.setLastName(lastname);
        em.setSurName(surname);
        em.setPosition(position);
        em.setAccount(account);
        em.setEMail(email);
        em.setPassword(password);
        em.setStatus(status);
        Assertions.assertEquals(id, em.getId());
        Assertions.assertEquals(firstname, em.getFirstName());
        Assertions.assertEquals(lastname, em.getLastName());
        Assertions.assertEquals(surname, em.getSurName());
        Assertions.assertEquals(position, em.getPosition());
        Assertions.assertEquals(account, em.getAccount());
        Assertions.assertEquals(email, em.getEMail());
        Assertions.assertEquals(password, em.getPassword());
        Assertions.assertEquals(status, em.getStatus());
    }
}