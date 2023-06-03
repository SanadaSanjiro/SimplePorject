package com.digdes.simple.dao;

import com.digdes.simple.dto.employee.EmployeeDTO;
import com.digdes.simple.dto.employee.EmployeeSrchDTO;
import com.digdes.simple.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeModel getById(Long id) {
        try {
            return employeeRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel create(EmployeeModel employeeModel) {
        try {
            return employeeRepository.save(employeeModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel update(EmployeeModel employeeModel) {
        try {
            employeeRepository.save(employeeModel);
            return employeeModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EmployeeModel> getAll() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel deleteById (Long id) {
        EmployeeModel employeeModel = getById(id);
        try {
            employeeRepository.deleteById(id);
            return employeeModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EmployeeModel> getFiltered(EmployeeSrchDTO dto) {
        try {
            return employeeRepository.findAll(EmployeeSpecification.getFilteres(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
     return null;
    }

    public EmployeeModel getByAccount(String account) {
        try {
            return employeeRepository.getByAccount(account).get();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }
}
