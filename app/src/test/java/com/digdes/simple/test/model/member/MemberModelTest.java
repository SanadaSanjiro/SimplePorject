package com.digdes.simple.test.model.member;

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.member.MemberModel;
import com.digdes.simple.member.MembersKey;
import com.digdes.simple.member.Role;
import com.digdes.simple.project.ProjectModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberModelTest {
    @Test
    @DisplayName("MemberModel setters and getters are ok")
    public void memberModel_CheckSettersGetters() {
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

        Assertions.assertEquals(mk, memberModel.getId());
        Assertions.assertEquals(employeeModel, memberModel.getEmployee());
        Assertions.assertEquals(projectModel, memberModel.getProject());
        Assertions.assertEquals(role, memberModel.getRole());
    }
}
