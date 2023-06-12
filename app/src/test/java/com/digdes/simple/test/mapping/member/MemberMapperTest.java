package com.digdes.simple.test.mapping.member;

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.member.*;
import com.digdes.simple.project.ProjectModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberMapperTest {
    @Test
    @DisplayName("MemberMapper maps all fields model->DTO")
    void memberMappper_MapModelToDTO() {
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeStatus status = EmployeeStatus.ACTIVE;
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(id);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setStatus(status);

        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String name = "Project001";
        projectModel.setCode(code);
        projectModel.setName(name);

        MembersKey mk = new MembersKey();
        mk.setEmpid(id);
        mk.setPrjcode(code);

        Role role= Role.TESTER;
        MemberModel memberModel = new MemberModel();
        memberModel.setId(mk);
        memberModel.setRole(role);
        memberModel.setEmployee(employeeModel);
        memberModel.setProject(projectModel);

        MemberDTO dto = MemberMapper.map(memberModel);
        Assertions.assertEquals(employeeModel.getId(), dto.getEmpid());
        Assertions.assertEquals(projectModel.getCode(), dto.getPrjcode());
        Assertions.assertEquals(role.toString(), dto.getRole());
    }

    @Test
    @DisplayName("MemberMapper maps all fields DTO->model")
    void memberMappper_MapDTOToModel() {
        final Long empid = 1L;
        final String prjcode = "p001";
        final Role role = Role.DEVELOPER;
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(empid);
        dto.setPrjcode(prjcode);
        dto.setRole(role.toString());

        MemberModel model = MemberMapper.map(dto);
        Assertions.assertEquals(empid, model.getId().getEmpid());
        Assertions.assertEquals(prjcode, model.getId().getPrjcode());
        Assertions.assertEquals(role, model.getRole());
    }
}
