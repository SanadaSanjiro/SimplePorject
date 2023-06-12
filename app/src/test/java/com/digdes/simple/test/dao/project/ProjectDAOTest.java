package com.digdes.simple.test.dao.project;

import com.digdes.simple.project.*;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProjectDAOTest extends BaseTest {
    @Value("${spring.datasource.url}")
    private String url;

    @Test
    void checkDatasourceUrl() {
        Assertions.assertNotEquals(
                "jdbc:postgresql://localhost:5432/taskmanagerdb", url);
    }

    @Mock
    ProjectRepository repository;

    @InjectMocks
    ProjectDAO projectDAO;

    @Test
    @DisplayName("Get Project from repo AllGoodConditions")
    public void getByCodeProject_AllGoodConditions() {
        ProjectModel model = testInit();
        String code = model.getCode();

        Optional<ProjectModel> optional = Optional.of(model);
        when(repository.getByCode(any())).thenReturn(optional);
        Assertions.assertEquals(model, projectDAO.getByCode(code));
        verify(repository, Mockito.times(1)).getByCode(Mockito.any());
    }

    @Test
    @DisplayName("Get Project from repo nothing found")
    public void getByCodeProject_NothingFound() {
        ProjectModel model = testInit();
        String code = model.getCode();

        when(repository.getByCode(any())).thenReturn(null);
        Assertions.assertNull(projectDAO.getByCode(code));
        verify(repository, Mockito.times(1)).getByCode(Mockito.any());
    }

    @Test
    @DisplayName("Create Project AllGoodConditions")
    public void createProject_AllGoodConditions() {
        ProjectModel model = testInit();

        when(repository.save(any())).thenReturn(model);
        Assertions.assertEquals(model, projectDAO.create(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Create Project but not created")
    public void createProject_NotCreated() {
        ProjectModel model = testInit();

        when(repository.save(any())).thenReturn(null);
        Assertions.assertNull(projectDAO.create(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Update Project AllGoodConditions")
    public void updateProject_AllGoodConditions() {
        ProjectModel model = testInit();

        when(repository.save(any())).thenReturn(model);
        Assertions.assertEquals(model, projectDAO.update(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Update Project but not created")
    public void updateProject_NotUpdated() {
        ProjectModel model = testInit();

        when(repository.save(any())).thenReturn(null);
        Assertions.assertNull(projectDAO.update(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("ChangeStatus Project AllGoodConditions")
    public void changeStatusProject_AllGoodConditions() {
        ProjectModel model = testInit();
        ProjectStatus status = ProjectStatus.DEVELOPING;
        model.setStatus(status);

        when(repository.save(any())).thenReturn(model);
        Assertions.assertEquals(model, projectDAO.changeStatus(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("ChangeStatus Project but failed")
    public void changeStatusProject_NotSaved() {
        ProjectModel model = testInit();
        ProjectStatus status = ProjectStatus.DEVELOPING;
        model.setStatus(status);

        when(repository.save(any())).thenReturn(null);
        Assertions.assertNull(projectDAO.create(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("GetFiltered Project AllGoodConditions")
    public void getFilteredProject_AllGoodConditions() {
        String prjName = "project001";
        ProjectSrchDTO dto = new ProjectSrchDTO();
        dto.setName(prjName);
        Assertions.assertNotNull(projectDAO.getFiltered(dto));
    }

    @Test
    @DisplayName("GetFiltered Project nothing found")
    public void getFilteredProject_NothingFound() {
        String prjName = "project001";
        ProjectSrchDTO dto = new ProjectSrchDTO();
        dto.setName(prjName);
        Assertions.assertTrue(projectDAO.getFiltered(dto).isEmpty());
    }

    @Test
    @DisplayName("Get all Project from repo AllGoodConditions")
    public void getAllProject_AllGoodConditions() {
        Assertions.assertNotNull(projectDAO.getAll());
        verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Get all Project from repo nothing found")
    public void getAllProject_NothingFound() {
        when(repository.findAll()).thenReturn(null);
        Assertions.assertNull(projectDAO.getAll());
        verify(repository, Mockito.times(1)).findAll();
    }

    private ProjectModel testInit() {
        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String prjName = "project001";
        ProjectStatus status = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(prjName);
        projectModel.setStatus(status);
        return projectModel;
    }
}
