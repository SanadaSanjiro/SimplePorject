package com.digdes.simple.test.dao.employee;

import com.digdes.simple.employee.EmployeeSpecification;
import com.digdes.simple.employee.EmployeeSrchDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeSpecificationTest {
    @Test
    @DisplayName("Test Employee Specification with non-empty DTO")
    public void getFiltersEmployee_NonEmptyDTO() {
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
        Assertions.assertNotNull(EmployeeSpecification.getFilters(dto));
    }

    @Test
    @DisplayName("Test Employee Specification with empty DTO")
    public void getFiltersEmployee_EmptyDTO() {
        EmployeeSrchDTO dto = new EmployeeSrchDTO();
        Assertions.assertNotNull(EmployeeSpecification.getFilters(dto));
    }
}