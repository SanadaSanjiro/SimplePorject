package com.digdes.simple.test.service.member;

import com.digdes.simple.employee.EmployeeDAO;
import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.member.*;
import com.digdes.simple.member.impl.MemberServiceImpl;
import com.digdes.simple.project.ProjectDAO;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MemberServiceImplTest extends BaseTest {
    @Mock
    MemberDAO memberDAO;

    @Mock
    EmployeeDAO employeeDAO;

    @Mock
    ProjectDAO projectDAO;

    @InjectMocks
    MemberServiceImpl memberService;

    /****************************************************************************
     * Tests of creation
     */

    @Test
    @DisplayName("Create Member when all conditions ok")
    public void createMember_AllGoodConditions() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;

        MemberDTO dto = new MemberDTO();
        dto.setEmpid(employeeModel.getId());
        dto.setPrjcode(projectModel.getCode());
        dto.setRole(role.toString());

        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(memberDAO.getById(any())).thenReturn(null);
        when(memberDAO.create(any())).thenReturn(memberModel);

        MemberDTO result = memberService.create(dto);
        Assertions.assertEquals(dto.getEmpid(), result.getEmpid());
        Assertions.assertEquals(dto.getPrjcode(), result.getPrjcode());
        Assertions.assertEquals(dto.getRole(), result.getRole());
    }

    @Test
    @DisplayName("Create Member empty DTO throws exception")
    public void createMember_emptyDTO_ThrowException() {
        MemberDTO dto = new MemberDTO();
        Assertions.assertThrows(ResponseStatusException.class, ()->memberService.create(dto));
    }

    @Test
    @DisplayName("Create Member no employee id throws exception")
    public void createMember_noEmployeeId_ThrowException() {
        String code = "001";
        Role role= Role.TESTER;
        MemberDTO dto = new MemberDTO();
        dto.setPrjcode(code);
        dto.setRole(role.toString());
        Assertions.assertThrows(ResponseStatusException.class, ()->memberService.create(dto));
    }

    @Test
    @DisplayName("Create Member no project code throws exception")
    public void createMember_noProjectCode_ThrowException() {
        Long id = 1L;
        Role role= Role.TESTER;
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setRole(role.toString());
        Assertions.assertThrows(ResponseStatusException.class, ()->memberService.create(dto));
    }

    @Test
    @DisplayName("Create Member no role throws exception")
    public void createMember_noRole_ThrowException() {
        Long id = 1L;
        String code = "001";
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        Assertions.assertThrows(ResponseStatusException.class, ()->memberService.create(dto));
    }

    @Test
    @DisplayName("Create Member no such Employee throws exception")
    public void createMember_noSuchEmployee_ThrowException() {
        ProjectModel projectModel = createProject();
        Long id = 1L;
        String code = "001";
        Role role= Role.TESTER;
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        dto.setRole(role.toString());

        when(employeeDAO.getById(any())).thenReturn(null);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(memberDAO.getById(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.create(dto));
    }

    @Test
    @DisplayName("Create Member Employee Deleted throws exception")
    public void createMember_EmployeeDeleted_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        Long id = 1L;
        String code = "001";
        Role role= Role.TESTER;
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        dto.setRole(role.toString());

        employeeModel.setStatus(EmployeeStatus.DELETED);

        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(projectDAO.getByCode(any())).thenReturn(null);
        when(memberDAO.getById(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.create(dto));
    }

    @Test
    @DisplayName("Create Member no such Project throws exception")
    public void createMember_noSuchProject_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        Long id = 1L;
        String code = "001";
        Role role= Role.TESTER;
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        dto.setRole(role.toString());

        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(projectDAO.getByCode(any())).thenReturn(null);
        when(memberDAO.getById(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.create(dto));
    }

    @Test
    @DisplayName("Create Member Employee already exists")
    public void createMember_EmployeeExists_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;

        MemberDTO dto = new MemberDTO();
        dto.setEmpid(employeeModel.getId());
        dto.setPrjcode(projectModel.getCode());
        dto.setRole(role.toString());

        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(memberDAO.getById(any())).thenReturn(memberModel);
        when(memberDAO.create(any())).thenReturn(memberModel);

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.create(dto));
    }

    /****************************************************************************
     * Delete tests
     */
    @Test
    @DisplayName("Delete Member when all conditions ok")
    public void deleteMember_AllGoodConditions() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        when(memberDAO.deleteById(any())).thenReturn(memberModel);
        String code = projectModel.getCode();
        Long id = employeeModel.getId();

        MemberDTO result = memberService.delete(code, id);
        Assertions.assertEquals(id, result.getEmpid());
        Assertions.assertEquals(code, result.getPrjcode());
        Assertions.assertEquals(role.toString(), result.getRole());
    }

    @Test
    @DisplayName("Delete Member no employee id throws exception")
    public void deleteMember_noEmployeeId_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        when(memberDAO.deleteById(any())).thenReturn(memberModel);
        String code = projectModel.getCode();

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.delete(code, null));
    }

    @Test
    @DisplayName("Delete Member no project code throws exception")
    public void deleteMember_noProjectCode_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        when(memberDAO.deleteById(any())).thenReturn(memberModel);
        Long id = employeeModel.getId();

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.delete(null, id));
    }

    @Test
    @DisplayName("Delete Member with no result")
    public void deleteMember_ResultNull() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        when(memberDAO.deleteById(any())).thenReturn(null);
        String code = projectModel.getCode();
        Long id = employeeModel.getId();

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.delete(code, id));
    }
    /****************************************************************************
     * getByPrjCode tests
     */
    @Test
    @DisplayName("GetByProjectCode Member when all conditions ok")
    public void getByPrjCode_AllGoodConditions() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        List<MemberModel> list = new ArrayList<>();
        list.add(memberModel);

        when(memberDAO.getByProject(any())).thenReturn(list);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        String code = projectModel.getCode();

        List<MemberDTO> resultList = memberService.getByPrjCode(code);
        Assertions.assertFalse(resultList.isEmpty());
    }

    @Test
    @DisplayName("GetByProjectCode Member no project code throws exception")
    public void getByPrjCode_noProjectCode_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class,()->memberService.getByPrjCode(null));
    }

    @Test
    @DisplayName("GetByProjectCode Member no such project throws exception")
    public void getByPrjCode_NoProjectFound_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        List<MemberModel> list = new ArrayList<>();
        list.add(memberModel);

        when(memberDAO.getByProject(any())).thenReturn(list);
        when(projectDAO.getByCode(any())).thenReturn(null);
        String code = projectModel.getCode();

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.getByPrjCode(code));
    }

    @Test
    @DisplayName("GetByProjectCode Member no result throws exception")
    public void getByPrjCode_ResultNull_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        List<MemberModel> list = new ArrayList<>();

        when(memberDAO.getByProject(any())).thenReturn(list);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        String code = projectModel.getCode();

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.getByPrjCode(code));
    }
    /****************************************************************************
     * getById tests
     */
    @Test
    @DisplayName("GetById Member when all conditions ok")
    public void getById_AllGoodConditions() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        Long id = employeeModel.getId();
        String code = projectModel.getCode();

        when(memberDAO.getById(any())).thenReturn(memberModel);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);

        MemberDTO result = memberService.getById(code, id);
        Assertions.assertEquals(id, result.getEmpid());
        Assertions.assertEquals(code, result.getPrjcode());
        Assertions.assertEquals(role.toString(), result.getRole());
    }

    @Test
    @DisplayName("GetById Member no project code throws exception")
    public void getById_noProjectCode_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        Long id = employeeModel.getId();
        Assertions.assertThrows(ResponseStatusException.class,()->memberService.getById(null, id));
    }

    @Test
    @DisplayName("GetById Member no employee id throws exception")
    public void getById_noEmployeeId_ThrowException() {
        ProjectModel projectModel = createProject();
        String code = projectModel.getCode();
        Assertions.assertThrows(ResponseStatusException.class,()->memberService.getById(code, null));
    }

    @Test
    @DisplayName("GetById Member no result throws exception")
    public void getById_ResultNull_ThrowException() {
        EmployeeModel employeeModel = createEmployee(1L);
        ProjectModel projectModel = createProject();
        MemberModel memberModel = createMember(employeeModel, projectModel);
        Role role= Role.TESTER;
        memberModel.setRole(role);

        Long id = employeeModel.getId();
        String code = projectModel.getCode();

        when(memberDAO.getById(any())).thenReturn(null);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);

        Assertions.assertThrows(ResponseStatusException.class,()->memberService.getById(code, id));
    }
    /****************************************************************************
     * Initialisation
     */
    private EmployeeModel createEmployee(Long id) {
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeStatus status = EmployeeStatus.ACTIVE;
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(id);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setStatus(status);
        return employeeModel;
    }

    private ProjectModel createProject() {
        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String name = "Project001";
        projectModel.setCode(code);
        projectModel.setName(name);
        return  projectModel;
    }

    private MemberModel createMember(EmployeeModel employeeModel, ProjectModel projectModel) {
        MembersKey mk = new MembersKey();
        mk.setEmpid(employeeModel.getId());
        mk.setPrjcode(projectModel.getCode());

        Role role= Role.TESTER;
        MemberModel memberModel = new MemberModel();
        memberModel.setId(mk);
        memberModel.setRole(role);
        memberModel.setEmployee(employeeModel);
        memberModel.setProject(projectModel);
        return memberModel;
    }
 }
