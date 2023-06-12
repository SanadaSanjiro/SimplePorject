package com.digdes.simple.service.member.impl;

import com.digdes.simple.dao.employee.EmployeeDAO;
import com.digdes.simple.dao.member.MemberDAO;
import com.digdes.simple.dao.project.ProjectDAO;
import com.digdes.simple.dto.member.MemberDTO;
import com.digdes.simple.dto.member.MemberDltDTO;
import com.digdes.simple.mapping.member.MemberMapper;
import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.employee.EmployeeStatus;
import com.digdes.simple.model.member.MemberModel;
import com.digdes.simple.model.member.MembersKey;
import com.digdes.simple.model.project.ProjectModel;
import com.digdes.simple.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    ProjectDAO projectDAO;


    @Override
    public MemberDTO create(MemberDTO dto) {
        // Проверяем, что DTO не пустое, и содержит обязательные поля код проекта и id сотрудника
        if (dto == null || dto.getEmpid() == null || dto.getPrjcode() ==null || dto.getRole() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // Проверяем, что сотрудник с таким id существеует
        EmployeeModel employeeModel = employeeDAO.getById(dto.getEmpid());
        if (employeeModel==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (employeeModel.getStatus()==EmployeeStatus.DELETED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ProjectModel projectModel = projectDAO.getByCode(dto.getPrjcode());
        // Проверяем, что проект с таким кодом существеует
        if (projectModel==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        MembersKey mk = new MembersKey();
        mk.setEmpid(dto.getEmpid());
        mk.setPrjcode(dto.getPrjcode());

        // Проверяем что данный сотрудник отсутсвует в этом проекте
        if ((memberDAO.getById(mk)!=null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        MemberModel model = MemberMapper.map(dto);
        model.setEmployee(employeeModel);
        model.setProject(projectModel);
        model = memberDAO.create(model);
        return MemberMapper.map(model);
    }

    @Override
    public MemberDTO delete(String code, Long id) {
        if (code == null || id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        MembersKey mk = new MembersKey();
        mk.setEmpid(id);
        mk.setPrjcode(code);
        MemberModel model = memberDAO.deleteById(mk);
        if (model==null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        return MemberMapper.map(model);
    }

    @Override
    public List<MemberDTO> getByPrjCode(String code) {
        if (code == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ProjectModel projectModel = projectDAO.getByCode(code);
        if (projectModel == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<MemberDTO> dtos = memberDAO.getByProject(projectModel)
                .stream()
                .map(m-> MemberMapper.map(m))
                .toList();
        if (dtos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return dtos;
    }

    @Override
    public MemberDTO getById(String code, Long id) {
        if (code == null || id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        MembersKey mk = new MembersKey();
        mk.setEmpid(id);
        mk.setPrjcode(code);
        MemberModel model = memberDAO.getById(mk);
        if (model==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return MemberMapper.map(model);
    }
}
