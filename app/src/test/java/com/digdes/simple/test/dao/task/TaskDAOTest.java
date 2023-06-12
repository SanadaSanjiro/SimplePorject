package com.digdes.simple.test.dao.task;

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.project.ProjectStatus;
import com.digdes.simple.task.*;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskDAOTest extends BaseTest {
    @Value("${spring.datasource.url}")
    private String url;

    @Test
    void checkDatasourceUrl() {
        Assertions.assertNotEquals(
                "jdbc:postgresql://localhost:5432/taskmanagerdb", url);
    }

    @Mock
    TaskRepository repository;

    @InjectMocks
    TaskDAO taskDAO;

    @Test
    @DisplayName("Get Task by id AllGoodConditions")
    public void getByIdTask_AllGoodConditions() {
        TaskModel model = testInit();
        Long id = model.getId();

        Optional<TaskModel> optional = Optional.of(model);
        when(repository.findById(any())).thenReturn(optional);
        Assertions.assertEquals(model, taskDAO.getById(id));
        verify(repository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Get Task by id nothing found")
    public void getByIdTask_NothingFound() {
        TaskModel model = testInit();
        Long id = model.getId();

        Optional<TaskModel> optional = Optional.of(model);
        when(repository.findById(any())).thenReturn(null);
        Assertions.assertNull(taskDAO.getById(id));
        verify(repository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Create Task AllGoodConditions")
    public void createTask_AllGoodConditions() {
        TaskModel model = testInit();

        when(repository.save(any())).thenReturn(model);
        Assertions.assertEquals(model, taskDAO.create(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Create Task but not created")
    public void createTask_NotCreated() {
        TaskModel model = testInit();

        when(repository.save(any())).thenReturn(null);
        Assertions.assertNull(taskDAO.create(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Update Task AllGoodConditions")
    public void updateTask_AllGoodConditions() {
        TaskModel model = testInit();

        when(repository.save(any())).thenReturn(model);
        Assertions.assertEquals(model, taskDAO.update(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Update Task but not created")
    public void updateTask_NotUpdated() {
        TaskModel model = testInit();

        when(repository.save(any())).thenReturn(null);
        Assertions.assertNull(taskDAO.update(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("ChangeStatus Task AllGoodConditions")
    public void changeStatusTask_AllGoodConditions() {
        TaskModel model = testInit();
        TaskStatus status = TaskStatus.PROCESSED;
        model.setStatus(status);

        when(repository.save(any())).thenReturn(model);
        Assertions.assertEquals(model, taskDAO.changeStatus(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("ChangeStatus Task but failed")
    public void changeStatusTask_NotSaved() {
        TaskModel model = testInit();
        TaskStatus status = TaskStatus.PROCESSED;
        model.setStatus(status);

        when(repository.save(any())).thenReturn(null);
        Assertions.assertNull(taskDAO.create(model));
        verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("GetFiltered Task AllGoodConditions")
    public void getFilteredTask_AllGoodConditions() {
        String taskName = "task01";
        TaskSrchDTO dto = new TaskSrchDTO();
        dto.setName(taskName);
        Assertions.assertNotNull(taskDAO.getFiltered(dto));
    }

    @Test
    @DisplayName("GetFiltered Task nothing found")
    public void getFilteredTask_NothingFound() {
        String taskName = "task01";
        TaskSrchDTO dto = new TaskSrchDTO();
        dto.setName(taskName);
        Assertions.assertTrue(taskDAO.getFiltered(dto).isEmpty());
    }

    @Test
    @DisplayName("Get Member by Project AllGoodConditions")
    public void getMemberByProject_AllGoodConditions() {
        TaskModel model = testInit();
        ProjectModel pm = model.getProject();

        List<TaskModel> list = new ArrayList<>();
        list.add(model);

        Optional<List<TaskModel>> optional = Optional.of(list);
        when(repository.getByProject(any())).thenReturn(optional);
        Assertions.assertEquals(list, taskDAO.getByProject(pm));
        verify(repository, Mockito.times(1)).getByProject(Mockito.any());
    }

    @Test
    @DisplayName("Get Member by Project Nothing found")
    public void getMemberByProject_NothingFound() {
        TaskModel model = testInit();
        ProjectModel pm = model.getProject();

        Assertions.assertNull(taskDAO.getByProject(pm));
        verify(repository, Mockito.times(1)).getByProject(Mockito.any());
    }


    private TaskModel testInit() {
        Long id = 100L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(id);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setStatus(employeeStatus);

        Long aId = 101L;
        final String afirstName = "FirstName";
        final String alastName = "LastName";
        EmployeeModel authorModel = new EmployeeModel();
        authorModel.setId(aId);
        authorModel.setFirstName(afirstName);
        authorModel.setLastName(alastName);
        authorModel.setStatus(employeeStatus);

        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String name = "Project001";
        ProjectStatus projectStatus = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(name);
        projectModel.setStatus(projectStatus);

        String taskName = "task01";
        String taskDescription = "some_description";
        TaskStatus taskStatus = TaskStatus.NEW;
        int laborCost = 8;
        LocalDate now = LocalDate.now();
        LocalDate executionDate = now.plusDays(2);
        TaskModel taskModel = new TaskModel();
        taskModel.setId(id);
        taskModel.setProject(projectModel);
        taskModel.setName(taskName);
        taskModel.setStatus(taskStatus);
        taskModel.setDetails(taskDescription);
        taskModel.setEmployee(employeeModel);
        taskModel.setAuthor(authorModel);
        taskModel.setLaborCost(laborCost);
        taskModel.setExecutiondate(executionDate);
        taskModel.setChangedate(now);
        taskModel.setCreationdate(now);
        return taskModel;
    }
}
