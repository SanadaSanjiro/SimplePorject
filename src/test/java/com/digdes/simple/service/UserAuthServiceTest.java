package com.digdes.simple.service;

import com.digdes.simple.BaseTest;
import com.digdes.simple.dao.employee.EmployeeDAO;
import com.digdes.simple.model.employee.EmployeeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserAuthServiceTest extends BaseTest {
    @Mock
    EmployeeDAO employeeDAO;

    @Mock
    PassEncoder pe;

    @InjectMocks
    UserAuthService userAuthService;

    @Test
    @DisplayName("Load User by account name when all conditions ok")
    public void loadUserByUsername_AllGoodConditions() {
        String account = "account";
        String password = "password";
        EmployeeModel em = new EmployeeModel();
        em.setAccount(account);
        em.setPassword(password);
        when(employeeDAO.getByAccount(any())).thenReturn(em);
        Assertions.assertNotNull(userAuthService.loadUserByUsername(account));
    }

    @Test
    @DisplayName("Load User by account user not found")
    public void loadUserByUsername_UserNotFound_ThrowException() {
        String account = "account";
        when(employeeDAO.getByAccount(any())).thenReturn(null);
        Assertions.assertThrowsExactly(UsernameNotFoundException.class,
                ()-> userAuthService.loadUserByUsername(account));
    }

    @Test
    @DisplayName("Admin initialization test")
    public void initAdminTest() {
        when(employeeDAO.getByAccount(any())).thenReturn(null);
        userAuthService.initAdmin();
        verify(employeeDAO, Mockito.times(1)).create(Mockito.any());
    }
}