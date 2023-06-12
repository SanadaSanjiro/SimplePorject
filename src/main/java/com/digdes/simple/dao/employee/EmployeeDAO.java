package com.digdes.simple.dao.employee;

import com.digdes.simple.dto.employee.EmployeeSrchDTO;
import com.digdes.simple.model.employee.EmployeeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeDAO {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeModel getById(Long id) {
        try {
            return employeeRepository.findById(id).get();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel create(EmployeeModel employeeModel) {
        try {
            return employeeRepository.save(employeeModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel update(EmployeeModel employeeModel) {
        try {
            return employeeRepository.save(employeeModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public List<EmployeeModel> getAll() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    /** данный метод не используется (удаленные сотрудники помечаются статусом DELETED
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
    **/

    public List<EmployeeModel> getFiltered(EmployeeSrchDTO dto) {
        try {
            return employeeRepository.findAll(EmployeeSpecification.getFilters(dto));
        } catch (Exception e) {
            //e.printStackTrace();
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
