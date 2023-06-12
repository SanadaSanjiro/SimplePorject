package com.digdes.simple.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long>, JpaSpecificationExecutor<EmployeeModel>
{
    Optional<EmployeeModel> getByAccount(String account);
}
