package com.digdes.simple.test.web;

import com.digdes.simple.EmployeeController;
import com.digdes.simple.employee.*;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeControllerTest extends BaseTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @Test
    @DisplayName("Get Employee by id with a controller AllGoodConditions")
    public void getByIdEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeViewDTO dto = new EmployeeViewDTO();
        dto.setId(1L);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        dto.setStatus(EmployeeStatus.ACTIVE.toString());
        when(employeeService.getById(any())).thenReturn(dto);
        Assertions.assertEquals(dto, employeeController.getById(id));
        verify(employeeService, Mockito.times(1)).getById(Mockito.any());
    }

    @Test
    @DisplayName("Create Employee with a controller AllGoodConditions")
    public void createEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeViewDTO dto = new EmployeeViewDTO();
        dto.setId(1L);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        dto.setStatus(EmployeeStatus.ACTIVE.toString());
        when(employeeService.create(any())).thenReturn(dto);
        EmployeeCrtDTO crtDTO = new EmployeeCrtDTO();
        crtDTO.setFirstname(firstName);
        crtDTO.setLastname(lastName);
        Assertions.assertEquals(dto, employeeController.create(crtDTO));
        verify(employeeService, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    @DisplayName("Update Employee with a controller AllGoodConditions")
    public void updateEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeViewDTO dto = new EmployeeViewDTO();
        dto.setId(1L);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        dto.setStatus(EmployeeStatus.ACTIVE.toString());
        when(employeeService.update(any())).thenReturn(dto);
        EmployeeUpdDTO updDTO = new EmployeeUpdDTO();
        updDTO.setId(id);
        updDTO.setFirstname(firstName);
        updDTO.setLastname(lastName);
        Assertions.assertEquals(dto, employeeController.update(updDTO));
        verify(employeeService, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    @DisplayName("Delete Employee with a controller AllGoodConditions")
    public void deleteEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeViewDTO dto = new EmployeeViewDTO();
        dto.setId(1L);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        dto.setStatus(EmployeeStatus.ACTIVE.toString());
        when(employeeService.delete(any())).thenReturn(dto);
        Assertions.assertEquals(dto, employeeController.delete(id));
        verify(employeeService, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    @DisplayName("Get filtered Employee with a controller AllGoodConditions")
    public void getFilteredEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeViewDTO dto = new EmployeeViewDTO();
        dto.setId(1L);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        dto.setStatus(EmployeeStatus.ACTIVE.toString());
        List<EmployeeViewDTO> list = new ArrayList<>();
        list.add(dto);
        when(employeeService.getFiltered(any())).thenReturn(list);
        EmployeeSrchDTO srchDTO = new EmployeeSrchDTO();
        srchDTO.setFirstname(firstName);
        srchDTO.setLastname(lastName);
        Assertions.assertEquals(list, employeeController.getFiltered(srchDTO));
        verify(employeeService, Mockito.times(1)).getFiltered(Mockito.any());
    }
}
