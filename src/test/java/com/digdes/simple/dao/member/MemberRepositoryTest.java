package com.digdes.simple.dao.member;

import com.digdes.simple.dao.employee.EmployeeDAO;
import com.digdes.simple.dao.project.ProjectDAO;
import com.digdes.simple.model.member.MemberModel;
import com.digdes.simple.model.member.MembersKey;
import com.digdes.simple.model.project.ProjectModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    ProjectDAO projectDAO;

    @Test
    public void memberGetByIdTest() {
        Long id = 3L;
        String projectCode = "p0000001";
        MembersKey mk = new MembersKey();
        mk.setEmpid(id);
        mk.setPrjcode(projectCode);
        MemberModel model = memberRepository.findById(mk).get();
        System.out.println(model);
    }

    @Test
    public void memberGetByProjectTest() {
        Long id = 3L;
        String projectCode = "p0000001";
        //EmployeeModel employeeModel = employeeDAO.getById(id);
        ProjectModel projectModel = projectDAO.getByCode(projectCode);
        MembersKey mk = new MembersKey();
        mk.setEmpid(id);
        mk.setPrjcode(projectCode);
        //MemberModel model = new MemberModel();
        //model.setId(mk);
        //model.setEmployee(employeeModel);
        //model.setProject(projectModel);
        //model = memberRepository.save(model);

        List<MemberModel> members = memberRepository.getByProject(projectModel).get();
        System.out.println(members);
    }
}
