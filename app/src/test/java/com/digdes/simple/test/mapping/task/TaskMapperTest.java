package com.digdes.simple.test.mapping.task;

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.project.ProjectStatus;
import com.digdes.simple.task.TaskDTO;
import com.digdes.simple.task.TaskMapper;
import com.digdes.simple.task.TaskModel;
import com.digdes.simple.task.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskMapperTest {
    @Test
    @DisplayName("TaskMapper maps all fields model->DTO")
    public void test () {
        Long id = 1L;                                               //id задачи
        String taskName = "task01";                                 //наименование задачи
        String taskDescription = "some_description";                //описание задачи
        int labourcost = 8;                                         //трудозатраты
        LocalDate executionDate = LocalDate.now().plusDays(2);      //дата исполнения
        TaskStatus taskStatus = TaskStatus.NEW;                     //статус
        LocalDate creationDate = LocalDate.now();                   //дата создания
        LocalDate changeDate = LocalDate.now();                     //дата изменения

        Long emplId = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(emplId);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setStatus(employeeStatus);

        Long aId = 2L;
        final String afirstName = "FirstName";
        final String alastName = "LastName";
        EmployeeModel authorModel = new EmployeeModel();
        employeeModel.setId(aId);
        employeeModel.setFirstName(afirstName);
        employeeModel.setLastName(alastName);
        employeeModel.setStatus(employeeStatus);

        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String name = "Project001";
        ProjectStatus projectStatus = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(name);
        projectModel.setStatus(projectStatus);

        TaskModel model = new TaskModel();
        model.setId(id);
        model.setProject(projectModel);
        model.setName(taskName);
        model.setDetails(taskDescription);
        model.setEmployee(employeeModel);
        model.setLaborCost(labourcost);
        model.setExecutiondate(executionDate);
        model.setStatus(taskStatus);
        model.setAuthor(authorModel);
        model.setCreationdate(creationDate);
        model.setChangedate(changeDate);

        TaskDTO dto = TaskMapper.map(model);

        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(projectModel.getCode(), dto.getCode());
        Assertions.assertEquals(taskName, dto.getName());
        Assertions.assertEquals(taskDescription, dto.getDetails());
        Assertions.assertEquals(employeeModel.getId(), dto.getEmployee());
        Assertions.assertEquals(labourcost, dto.getLaborCost());
        Assertions.assertEquals(executionDate, dto.getExecutionDate());
        Assertions.assertEquals(taskStatus.toString(), dto.getStatus());
        Assertions.assertEquals(authorModel.getId(), dto.getAuthor());
        Assertions.assertEquals(creationDate, dto.getCreationDate());
        Assertions.assertEquals(changeDate, dto.getChangeDate());
    }
}
