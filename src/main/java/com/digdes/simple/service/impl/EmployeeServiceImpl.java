package com.digdes.simple.service.impl;

import com.digdes.simple.dao.employee.EmployeeDAO;
import com.digdes.simple.dto.employee.*;
import com.digdes.simple.mapping.employee.EmployeeCrtMapper;
import com.digdes.simple.mapping.employee.EmployeeUpdMapper;
import com.digdes.simple.mapping.employee.EmployeeViewMapper;
import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.employee.EmployeeStatus;
import com.digdes.simple.service.EmployeeService;
import com.digdes.simple.service.PassEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    private final PassEncoder passEncoder;



    @Override
    public EmployeeViewDTO getById(Long id) {
        EmployeeModel model = employeeDAO.getById(id);
        if (model==null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        return EmployeeViewMapper.map(model);
    }

    @Override
    public EmployeeViewDTO create(EmployeeCrtDTO dto) {
        EmployeeModel model = EmployeeCrtMapper.map(dto);
        if(!ObjectUtils.isEmpty(model.getPassword())) {
            model.setPassword(passEncoder.encode(model.getPassword())); // шифрует пароль для сохранения в БД
        }
        model.setStatus(EmployeeStatus.ACTIVE); // устанавливает статус сотрудника в АКТИВНЫЙ
        model = employeeDAO.create(model);
        return EmployeeViewMapper.map(model);
    }

    @Override
    public EmployeeViewDTO update(EmployeeUpdDTO dto) {
        EmployeeModel model = EmployeeUpdMapper.map(dto);
        Long id = model.getId();
        if (employeeDAO.getById(id).getStatus().equals(EmployeeStatus.DELETED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        model.setStatus(EmployeeStatus.ACTIVE);
        if(!ObjectUtils.isEmpty(model.getPassword())) {
            model.setPassword(employeeDAO.getById(id).getPassword());
        }
        model = employeeDAO.update(model);
        return EmployeeViewMapper.map(model);
    }

    @Override
    public List<EmployeeViewDTO> getAll() {
        List<EmployeeViewDTO> dtos = employeeDAO.getAll()
                .stream()
                .map(m-> EmployeeViewMapper.map(m))
                .toList();
        return dtos;
    }

    @Override
    public EmployeeViewDTO delete(Long id) {
        EmployeeModel model = employeeDAO.getById(id);
        if (model==null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        model.setStatus(EmployeeStatus.DELETED);
        return EmployeeViewMapper.map(employeeDAO.update(model));
    }

    @Override
    public List<EmployeeViewDTO> getFiltered(EmployeeSrchDTO dto) {
        List<EmployeeViewDTO> dtos = employeeDAO.getFiltered(dto)
                .stream()
                .map(m-> EmployeeViewMapper.map(m))
                .toList();
        return dtos;
    }
}
