package com.digdes.simple.test.dao.employee;

import com.digdes.simple.employee.*;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeDAOTest extends BaseTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Test
    void checkDatasourceUrl() {
        Assertions.assertNotEquals(
                "jdbc:postgresql://localhost:5432/taskmanagerdb", url);
    }
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeDAO employeeDAO;

    @Test
    @DisplayName("Get Employee from repo AllGoodConditions")
    public void getByIdEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(1L);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        Optional<EmployeeModel> optional = Optional.of(dbModel);
        when(employeeRepository.findById(any())).thenReturn(optional);
        Assertions.assertEquals(dbModel, employeeDAO.getById(id));
        verify(employeeRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Get Employee from repo nothing found")
    public void getByIdEmployee_NothingFound() {
        Long id = 1L;
        when(employeeRepository.findById(any())).thenReturn(null);
        Assertions.assertNull(employeeDAO.getById(id));
        verify(employeeRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Create Employee AllGoodConditions")
    public void createEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(1L);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        when(employeeRepository.save(any())).thenReturn(dbModel);
        Assertions.assertEquals(dbModel, employeeDAO.create(dbModel));
        verify(employeeRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Create Employee but not created")
    public void createEmployee_NotCreated() {
        EmployeeModel dbModel = new EmployeeModel();
        when(employeeRepository.save(any())).thenReturn(null);
        Assertions.assertNull(employeeDAO.create(dbModel));
        verify(employeeRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Update Employee AllGoodConditions")
    public void updateEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(1L);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        when(employeeRepository.save(any())).thenReturn(dbModel);
        Assertions.assertEquals(dbModel, employeeDAO.create(dbModel));
        verify(employeeRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Update Employee but not created")
    public void updateEmployee_NotUpdated() {
        EmployeeModel dbModel = new EmployeeModel();
        when(employeeRepository.save(any())).thenReturn(null);
        Assertions.assertNull(employeeDAO.create(dbModel));
        verify(employeeRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Get all Employee from repo AllGoodConditions")
    public void getAllEmployee_AllGoodConditions() {
        Assertions.assertNotNull(employeeDAO.getAll());
        verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Get all Employee from repo nothing found")
    public void getAllEmployee_NothingFound() {
        when(employeeRepository.findAll()).thenReturn(null);
        Assertions.assertNull(employeeDAO.getAll());
        verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("GetFiltered Employee from repo AllGoodConditions")
    public void getFilteredEmployee_AllGoodConditions() {
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeSrchDTO dto = new EmployeeSrchDTO();
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        Assertions.assertNotNull(employeeDAO.getFiltered(dto));
    }

    @Test
    @DisplayName("GetFiltered Employee from repo nothing found")
    public void getFilteredEmployee_NothingFound() {
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeSrchDTO dto = new EmployeeSrchDTO();
        dto.setFirstname(firstName);
        dto.setLastname(lastName);
        Assertions.assertTrue(employeeDAO.getFiltered(dto).isEmpty());
    }

    @Test
    @DisplayName("Get Employee by account from repo AllGoodConditions")
    public void getByAccountEmployee_AllGoodConditions() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String account = "account";
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setId(1L);
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setAccount(account);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        Optional<EmployeeModel> optional = Optional.of(dbModel);
        when(employeeRepository.getByAccount(account)).thenReturn(optional);
        Assertions.assertEquals(dbModel, employeeDAO.getByAccount(account));
        verify(employeeRepository, Mockito.times(1)).getByAccount(Mockito.any());
    }

    @Test
    @DisplayName("Get Employee by account from repo nothing found")
    public void getByAccountEmployee_NothingFound() {
        final String account = "account";
        Assertions.assertNull(employeeDAO.getByAccount(account));
    }
}