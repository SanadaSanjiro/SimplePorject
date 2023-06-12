package com.digdes.simple.test.service.project;

import com.digdes.simple.project.*;
import com.digdes.simple.project.impl.ProjectServiceImpl;
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

public class ProjectServiceImplTest extends BaseTest {
    @Mock
    ProjectDAO projectDAO;

    @InjectMocks
    ProjectServiceImpl projectService;

    /****************************************************************************
     * Tests of creation
     */
    @Test
    @DisplayName("Create Project when all conditions ok")
    public void createProject_AllGoodConditions() {
        ProjectModel model = createProject();

        ProjectCrtDTO dto = new ProjectCrtDTO();
        dto.setCode(model.getCode());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        when(projectDAO.create(any())).thenReturn(model);

        ProjectDTO resul = projectService.create(dto);
        Assertions.assertEquals(dto.getCode(), resul.getCode());
        Assertions.assertEquals(dto.getName(), resul.getName());
        Assertions.assertEquals(dto.getDescription(), resul.getDescription());
        Assertions.assertEquals(model.getStatus().toString(), resul.getStatus());
    }

    @Test
    @DisplayName("Create Project empty dto throws exception")
    public void createProject_emptyDTO_ThrowException() {
        ProjectCrtDTO dto = new ProjectCrtDTO();
        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.create(dto));
    }

    @Test
    @DisplayName("Create Project empty name throws exception")
    public void createProject_emptyName_ThrowException() {
        String code = "001";
        String description = "some_project";
        ProjectCrtDTO dto = new ProjectCrtDTO();
        dto.setCode(code);
        dto.setDescription(description);
        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.create(dto));
    }

    @Test
    @DisplayName("Create Project that already exists throws exception")
    public void createProject_CodeExists_ThrowException() {
        ProjectModel model = createProject();

        ProjectCrtDTO dto = new ProjectCrtDTO();
        dto.setCode(model.getCode());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        when(projectDAO.getByCode(any())).thenReturn(model);

        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.create(dto));
    }

    /****************************************************************************
     * getById tests
     */
    @Test
    @DisplayName("GetByCode Project when all conditions ok")
    public void getByCodeProject_AllGoodConditions() {
        ProjectModel model = createProject();
        String code = model.getCode();

        when(projectDAO.getByCode(any())).thenReturn(model);

        ProjectDTO resul = projectService.getByCode(code);
        Assertions.assertEquals(model.getCode(), resul.getCode());
        Assertions.assertEquals(model.getName(), resul.getName());
        Assertions.assertEquals(model.getDescription(), resul.getDescription());
        Assertions.assertEquals(model.getStatus().toString(), resul.getStatus());
    }

    @Test
    @DisplayName("GetByCode Project no code Throw Exception")
    public void getByCodeProject_NoCode_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.getByCode(null));
    }

    @Test
    @DisplayName("GetByCode Project no result Throw Exception")
    public void getByCodeProject_NoResult_ThrowException() {
        ProjectModel model = createProject();
        String code = model.getCode();

        when(projectDAO.getByCode(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.getByCode(code));
    }

    /****************************************************************************
     * update tests
     */
    @Test
    @DisplayName("Update Project when all conditions ok")
    public void updateProject_AllGoodConditions() {
        ProjectModel model = createProject();

        when(projectDAO.update(any())).thenReturn(model);
        when(projectDAO.getByCode(any())).thenReturn(model);

        ProjectCrtDTO dto = new ProjectCrtDTO();
        dto.setCode(model.getCode());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        ProjectDTO result = projectService.update(dto);
        Assertions.assertEquals(model.getCode(), result.getCode());
        Assertions.assertEquals(model.getName(), result.getName());
        Assertions.assertEquals(model.getDescription(), result.getDescription());
        Assertions.assertEquals(model.getStatus().toString(), result.getStatus());
    }

    @Test
    @DisplayName("Update Project empty DTO Throw Exception")
    public void updateProject_emptyDTO_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.update(null));
    }

    @Test
    @DisplayName("Update Project no name Throw Exception")
    public void updateProject_NoName_ThrowException() {
        ProjectModel model = createProject();

        when(projectDAO.update(any())).thenReturn(model);
        when(projectDAO.getByCode(any())).thenReturn(model);

        ProjectCrtDTO dto = new ProjectCrtDTO();
        dto.setCode(model.getCode());
        dto.setDescription(model.getDescription());

        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.update(null));
    }

    @Test
    @DisplayName("Update Project no code Throw Exception")
    public void updateProject_NoCode_ThrowException() {
        ProjectModel model = createProject();

        when(projectDAO.update(any())).thenReturn(model);
        when(projectDAO.getByCode(any())).thenReturn(model);

        ProjectCrtDTO dto = new ProjectCrtDTO();
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.update(null));
    }

    @Test
    @DisplayName("Update Project that not exist throws exception")
    public void updateProject_NotExist_ThrowException() {
        ProjectModel model = createProject();

        ProjectCrtDTO dto = new ProjectCrtDTO();
        dto.setCode(model.getCode());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        when(projectDAO.getByCode(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.update(dto));
    }
    /****************************************************************************
     * changeStatus tests
     */
    @Test
    @DisplayName("ChangeStatus Project when all conditions ok")
    public void changeStatusProject_AllGoodConditions() {
        ProjectModel model = createProject();
        model.setStatus(ProjectStatus.DRAFT);
        String code = model.getCode();

        when(projectDAO.changeStatus(any())).thenReturn(model);
        when(projectDAO.getByCode(any())).thenReturn(model);

        ProjectDTO result = projectService.changeStatus(code);
        Assertions.assertEquals(ProjectStatus.DEVELOPING.toString(), result.getStatus());
        result = projectService.changeStatus(code);
        Assertions.assertEquals(ProjectStatus.TESTING.toString(), result.getStatus());
        result = projectService.changeStatus(code);
        Assertions.assertEquals(ProjectStatus.COMPLETE.toString(), result.getStatus());
        result = projectService.changeStatus(code);
        Assertions.assertEquals(ProjectStatus.COMPLETE.toString(), result.getStatus());
    }

    @Test
    @DisplayName("ChangeStatus Project no code Throw Exception")
    public void changeStatusProject_NoCode_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.changeStatus(null));
    }

    @Test
    @DisplayName("ChangeStatus Project no such project Throw Exception")
    public void changeStatusProject_NoSuchProject_ThrowException() {
        ProjectModel model = createProject();
        String code = model.getCode();

        when(projectDAO.update(any())).thenReturn(model);
        when(projectDAO.getByCode(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.changeStatus(code));
    }

    /****************************************************************************
     * getFiltered tests
     */
    @Test
    @DisplayName("GetFiltered Project when all conditions ok")
    public void getFilteredProject_AllGoodConditions() {
        ProjectModel model = createProject();
        List<ProjectModel> list = new ArrayList<>();
        list.add(model);

        when(projectDAO.getFiltered(any())).thenReturn(list);
        ProjectSrchDTO dto = new ProjectSrchDTO();
        dto.setName(model.getName());

        Assertions.assertFalse(projectService.getFiltered(dto).isEmpty());
    }

    @Test
    @DisplayName("GetFiltered Project empty DTO Throws Exception")
    public void getFilteredProject_EmptyDTO_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class, ()->projectService.getFiltered(null));
    }

    /****************************************************************************
     * getAlld tests
     */
    @Test
    @DisplayName("GetAll Project when all conditions ok")
    public void getAllProject_AllGoodConditions() {
        ProjectModel model = createProject();
        List<ProjectModel> list = new ArrayList<>();
        list.add(model);

        when(projectDAO.getAll()).thenReturn(list);

        Assertions.assertFalse(projectService.getAll().isEmpty());
    }

    @Test
    @DisplayName("GetAll Project no results")
    public void getAllProject_NoResults() {
        Assertions.assertTrue(projectService.getAll().isEmpty());
    }

    /****************************************************************************
     * Initialisation
     */
    private ProjectModel createProject() {
        String code = "001";
        String prjName = "project001";
        String description = "some_project";
        ProjectStatus status = ProjectStatus.DRAFT;
        ProjectModel model = new ProjectModel();
        model.setCode(code);
        model.setName(prjName);
        model.setDescription(description);
        model.setStatus(status);
        return model;
    }
}
