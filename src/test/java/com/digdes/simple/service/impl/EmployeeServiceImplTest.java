package com.digdes.simple.service.impl;

import com.digdes.simple.BaseTest;
import com.digdes.simple.dao.employee.EmployeeDAO;
import com.digdes.simple.dto.employee.EmployeeCrtDTO;
import com.digdes.simple.dto.employee.EmployeeSrchDTO;
import com.digdes.simple.dto.employee.EmployeeUpdDTO;
import com.digdes.simple.dto.employee.EmployeeViewDTO;
import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.employee.EmployeeStatus;
import com.digdes.simple.service.PassEncoder;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceImplTest extends BaseTest {
    @Mock
    EmployeeDAO employeeDAO;

    @Mock
    PassEncoder pe;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("Create Employee when all conditions ok")
    public void createEmployee_AllGoodConditions() {
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(1L);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        when(employeeDAO.create(any())).thenReturn(dbModel);
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        EmployeeViewDTO result = employeeService.create(dto);
        verify(employeeDAO, Mockito.times(1)).create(Mockito.any());
        Assertions.assertEquals(firstName, result.getFirstname());
        Assertions.assertEquals(lastName, result.getLastname());
        Assertions.assertEquals("ACTIVE", result.getStatus());
        Assertions.assertNotNull(result.getId());
    }

    @Test
    @DisplayName("Create Employee without last name")
    public void createEmployee_DTOwithoutLastname_ThrowException() {
        final String firstName = "FirstName";
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        dto.setFirstname(firstName);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.create(dto));
    }

    @Test
    @DisplayName("Create Employee without first name")
    public void createEmployee_DTOwithoutFirstname_ThrowException() {
        final String lastName = "LastName";
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        dto.setLastname(lastName);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.create(dto));
    }


    @Test
    @DisplayName("Create Employee with null instead of DTO")
    public void createEmployee_NullDTO_ThrowException() {
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.create(null));
    }

    @Test
    @DisplayName("Create Employee with exiting account name")
    public void createEmployee_AccountAlreadyExists_ThrowException() {
        EmployeeModel em = new EmployeeModel();
        final String lastName = "LastName";
        final String firstName = "FirstName";
        final String account = "account";
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        dto.setLastname(lastName);
        dto.setFirstname(firstName);
        dto.setAccount(account);
        when(employeeDAO.getByAccount(any())).thenReturn(em);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.create(dto));
    }

    @Test
    @DisplayName("GetById Employee with null instead of id")
    void getByIdEmployee_NullId_ThrowException() {
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.getById(null));
    }

    @Test
    @DisplayName("GetById Employee not found")
    void getByIdEmployee_NoSearchResult_ThrowException() {
        Long id = 1L;
        when(employeeDAO.getById(any())).thenReturn(null);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.getById(id));
    }

    @Test
    @DisplayName("GetById Employee when all conditions ok")
    void getByIdEmployee_AllGoodConditions() {
        final String firstName = "FirstName";
        final String lastName = "LastName";
        Long id = 1L;
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(id);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        when(employeeDAO.getById(any())).thenReturn(dbModel);
        EmployeeViewDTO result = employeeService.getById(id);
        verify(employeeDAO, Mockito.times(1)).getById(Mockito.any());
        Assertions.assertEquals(firstName, result.getFirstname());
        Assertions.assertEquals(lastName, result.getLastname());
        Assertions.assertEquals("ACTIVE", result.getStatus());
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    @DisplayName("Update Employee when all conditions ok")
    public void updateEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String account = "account";
        EmployeeUpdDTO dto = new EmployeeUpdDTO();
        dto.setId(id);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        dto.setAccount(account);
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(1L);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setAccount(account);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        when(employeeDAO.getByAccount(any())).thenReturn(null);
        when(employeeDAO.update(any())).thenReturn(dbModel);
        when(employeeDAO.getById(any())).thenReturn(dbModel);
        EmployeeViewDTO result = employeeService.update(dto);
        verify(employeeDAO, Mockito.times(1)).update(Mockito.any());
        Assertions.assertEquals(firstName, result.getFirstname());
        Assertions.assertEquals(lastName, result.getLastname());
        Assertions.assertEquals("ACTIVE", result.getStatus());
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    @DisplayName("Update Employee without last name")
    public void updateEmployee_DTOwithoutLastname_ThrowException() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String account = "account";
        EmployeeUpdDTO dto = new EmployeeUpdDTO();
        dto.setId(id);
        dto.setFirstname(firstName);
        dto.setAccount(account);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.update(dto));
    }

    @Test
    @DisplayName("Update Employee without first name")
    public void updateEmployee_DTOwithoutFirstname_ThrowException() {
        Long id = 1L;
        final String lastName = "LastName";
        final String account = "account";
        EmployeeUpdDTO dto = new EmployeeUpdDTO();
        dto.setId(id);
        dto.setFirstname(lastName);
        dto.setAccount(account);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.update(dto));
    }

    @Test
    @DisplayName("Update Employee with null instead of DTO")
    void updateEmployee_NullDTO_ThrowException() {
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.update(null));
    }

    @Test
    @DisplayName("Update Employee with exiting account name")
    public void updateEmployee_AccountAlreadyExists_ThrowException() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String account = "account";
        EmployeeUpdDTO dto = new EmployeeUpdDTO();
        dto.setId(id);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        dto.setAccount(account);
        EmployeeModel em = new EmployeeModel();
        when(employeeDAO.getByAccount(any())).thenReturn(em);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.update(dto));
    }

    @Test
    @DisplayName("Update Employee deleted Employee")
    public void updateEmployee_Deleted_ThrowException() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeUpdDTO dto = new EmployeeUpdDTO();
        dto.setId(id);
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(1L);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setStatus(EmployeeStatus.DELETED);
        when(employeeDAO.getById(any())).thenReturn(dbModel);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.update(dto));
    }

    @Test
    @DisplayName("getAll Employees returns list")
    void getAllEmployees() {
        Assertions.assertNotNull(employeeService.getAll());
    }

    @Test
    @DisplayName("Delete Employee when all conditions ok")
    void deleteEmployee_AllGoodConditions() {
        Long id = 1L;
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(id);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        when(employeeDAO.getById(any())).thenReturn(dbModel);
        when(employeeDAO.update(any())).thenReturn(dbModel);
        EmployeeViewDTO result = employeeService.delete(id);
        verify(employeeDAO, Mockito.times(1)).update(Mockito.any());
        Assertions.assertEquals("DELETED", result.getStatus());
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    @DisplayName("Delete Employee with null instead of id")
    void deleteEmployee_NullId_ThrowException() {
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.delete(null));
    }

    @Test
    @DisplayName("Delete Employee not found")
    void deleteEmployee_NotFound_ThrowException() {
        Long id = 1L;
        when(employeeDAO.getById(any())).thenReturn(null);
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.delete(id));
    }

    @Test
    void getFilteredEmployee_AllGoodConditions() {
        EmployeeSrchDTO dto = new EmployeeSrchDTO();
        Assertions.assertNotNull(employeeService.getFiltered(dto));
    }

    @Test
    void getFilteredEmployee_NullDTO_ThrowException() {
        Assertions.assertThrowsExactly(ResponseStatusException.class, () ->employeeService.getFiltered(null));
    }
}
