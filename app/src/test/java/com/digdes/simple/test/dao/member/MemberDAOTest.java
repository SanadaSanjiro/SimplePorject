package com.digdes.simple.test.dao.member;

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.member.*;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberDAOTest extends BaseTest {
    @Value("${spring.datasource.url}")
    private String url;

    @Test
    void checkDatasourceUrl() {
        Assertions.assertNotEquals(
                "jdbc:postgresql://localhost:5432/taskmanagerdb", url);
    }

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberDAO memberDAO;

    @Test
    @DisplayName("Get Member from repo AllGoodConditions")
    public void getByIdMember_AllGoodConditions() {
        MemberModel memberModel = testInit();
        MembersKey mk = memberModel.getId();

        Optional<MemberModel> optional = Optional.of(memberModel);
        when(memberRepository.findById(any())).thenReturn(optional);
        Assertions.assertEquals(memberModel, memberDAO.getById(mk));
        verify(memberRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Get Member from repo nothing found")
    public void getByIdMember_NothingFound() {
        MemberModel memberModel = testInit();
        MembersKey mk = memberModel.getId();

        when(memberRepository.findById(any())).thenReturn(null);
        Assertions.assertNull(memberDAO.getById(mk));
        verify(memberRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Create Member AllGoodConditions")
    public void createMember_AllGoodConditions() {
        MemberModel memberModel = testInit();

        when(memberRepository.save(any())).thenReturn(memberModel);
        Assertions.assertEquals(memberModel, memberDAO.create(memberModel));
        verify(memberRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Create Member but not created")
    public void createMember_NotCreated() {
        MemberModel memberModel = testInit();

        when(memberRepository.save(any())).thenReturn(null);
        Assertions.assertNull(memberDAO.create(memberModel));
        verify(memberRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Delete Member AllGoodConditions")
    public void deleteMember_AllGoodConditions() {
        MemberModel memberModel = testInit();
        MembersKey mk = memberModel.getId();

        Optional<MemberModel> optional = Optional.of(memberModel);
        when(memberRepository.findById(any())).thenReturn(optional);
        Assertions.assertEquals(memberModel, memberDAO.deleteById(mk));
        verify(memberRepository, Mockito.times(1)).findById(Mockito.any());
        verify(memberRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Delete Member but it is not present")
    public void deleteMember_NotFound() {
        MemberModel memberModel = testInit();
        MembersKey mk = memberModel.getId();

        Assertions.assertNull(memberDAO.deleteById(mk));
        verify(memberRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Get Member by Project AllGoodConditions")
    public void getMemberByProject_AllGoodConditions() {
        MemberModel memberModel = testInit();
        ProjectModel pm = memberModel.getProject();

        List<MemberModel> list = new ArrayList<>();
        list.add(memberModel);

        Optional<List<MemberModel>> optional = Optional.of(list);
        when(memberRepository.getByProject(any())).thenReturn(optional);
        Assertions.assertEquals(list, memberDAO.getByProject(pm));
        verify(memberRepository, Mockito.times(1)).getByProject(Mockito.any());
    }

    @Test
    @DisplayName("Get Member by Project Nothing found")
    public void getMemberByProject_NothingFound() {
        MemberModel memberModel = testInit();
        ProjectModel pm = memberModel.getProject();

        Assertions.assertNull(memberDAO.getByProject(pm));
        verify(memberRepository, Mockito.times(1)).getByProject(Mockito.any());
    }

    private MemberModel testInit() {
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
        return memberModel;
    }
}

