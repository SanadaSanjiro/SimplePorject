package com.digdes.simple.service;

import com.digdes.simple.dao.EmployeeDAO;
import com.digdes.simple.model.EmployeeModel;
import com.digdes.simple.model.EmployeeStatus;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final EmployeeDAO employeeDAO;

    private final PassEncoder passEncoder;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
            EmployeeModel em = employeeDAO.getByAccount(account);
            if (em==null) {throw new UsernameNotFoundException("User not found");}
        return new org.springframework.security.core.userdetails.User(em.getAccount(), em.getPassword(),
                Collections.emptyList());
    }

    @PostConstruct
    public void initAdmin() {
        EmployeeModel em=employeeDAO.getByAccount("admin");
        if (em==null) {
            em = new EmployeeModel();
            em.setAccount("admin");
            em.setPassword(passEncoder.encode("admin"));
            em.setFirstName("Администратор");
            em.setLastName("Администратор");
            em.setStatus(EmployeeStatus.ACTIVE);
            employeeDAO.create(em);
        }
    }
}
