package com.digdes.simple.test.web;

import com.digdes.simple.TaskController;
import com.digdes.simple.task.*;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskControllerTest extends BaseTest {
    @Mock
    TaskService service;

    @InjectMocks
    TaskController controller;

    @Test
    @DisplayName("Get Task by id with a controller AllGoodConditions")
    public void getByIdTask_AllGoodConditions() {
        TaskDTO dto = createDTO();
        Long id = dto.getId();

        when(service.getById(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.getById(id));
        verify(service, Mockito.times(1)).getById(Mockito.any());
    }

    @Test
    @DisplayName("Create Task with a controller AllGoodConditions")
    public void createTask_AllGoodConditions() {
        TaskDTO dto = createDTO();
        TaskCrtDTO crtDTO = createCrtDTO();

        when(service.create(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.create(crtDTO));
        verify(service, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    @DisplayName("Update Task with a controller AllGoodConditions")
    public void updateTask_AllGoodConditions() {
        TaskDTO dto = createDTO();
        TaskUpdDTO updDTO = createUpdDTO();

        when(service.update(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.update(updDTO));
        verify(service, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    @DisplayName("ChangeStatus Task with a controller AllGoodConditions")
    public void changeStatusTask_AllGoodConditions() {
        TaskDTO dto = createDTO();
        Long id = dto.getId();

        when(service.changeStatus(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.changeStatus(id));
        verify(service, Mockito.times(1)).changeStatus(Mockito.any());
    }

    @Test
    @DisplayName("Get Task by Project code with a controller AllGoodConditions")
    public void getByPrjCodeTask_AllGoodConditions() {
        TaskDTO dto = createDTO();
        String code = dto.getCode();
        List<TaskDTO> list = new ArrayList<>();
        list.add(dto);

        when(service.getByPrjCode(any())).thenReturn(list);
        Assertions.assertFalse(controller.getByPrjCode(code).isEmpty());
        verify(service, Mockito.times(1)).getByPrjCode(Mockito.any());
    }

    @Test
    @DisplayName("GetFiltered Task with a controller AllGoodConditions")
    public void getFilteredTask_AllGoodConditions() {
        TaskDTO dto = createDTO();
        String code = dto.getCode();
        List<TaskDTO> list = new ArrayList<>();
        list.add(dto);

        TaskSrchDTO srchDTO = new TaskSrchDTO();
        srchDTO.setName(dto.getName());

        when(service.getFiltered(any())).thenReturn(list);
        Assertions.assertFalse(controller.getFiltered(srchDTO).isEmpty());
        verify(service, Mockito.times(1)).getFiltered(Mockito.any());
    }

    private TaskDTO createDTO() {
        Long id = 1L;                                               //id задачи
        String code = "project1";                                   //код проекта
        String taskName = "task01";                                 //наименование задачи
        String taskDescription = "some_description";                //описание задачи
        Long empid = 1L;                                            //id исполнителя
        int labourcost = 8;                                         //трудозатраты
        LocalDate executionDate = LocalDate.now().plusDays(2);      //дата исполнения
        TaskStatus taskStatus = TaskStatus.NEW;                     //статус
        Long authId = 2L;                                           //id автора задачи
        LocalDate creationDate = LocalDate.now();                   //дата создания
        LocalDate changeDate = LocalDate.now();                     //дата изменения

        TaskDTO dto = new TaskDTO();
        dto.setId(id);
        dto.setCode(code);
        dto.setName(taskName);
        dto.setDetails(taskDescription);
        dto.setEmployee(empid);
        dto.setLaborCost(labourcost);
        dto.setExecutionDate(executionDate);
        dto.setStatus(taskStatus.toString());
        dto.setAuthor(authId);
        dto.setCreationDate(creationDate);
        dto.setChangeDate(changeDate);
        return dto;
    }

    private TaskCrtDTO createCrtDTO() {
        String code = "project1";                                   //код проекта
        String taskName = "task01";                                 //наименование задачи
        String taskDescription = "some_description";                //описание задачи
        Long empid = 1L;                                            //id исполнителя
        int labourcost = 8;                                         //трудозатраты
        LocalDate executionDate = LocalDate.now().plusDays(2);      //дата исполнения

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(code);
        dto.setName(taskName);
        dto.setDetails(taskDescription);
        dto.setEmployee(empid);
        dto.setLaborCost(labourcost);
        dto.setExecutionDate(executionDate);
        return dto;
    }

    private TaskUpdDTO createUpdDTO() {
        Long id = 1L;                                               //id задачи
        String code = "project1";                                   //код проекта
        String taskName = "task01";                                 //наименование задачи
        String taskDescription = "some_description";                //описание задачи
        Long empid = 1L;                                            //id исполнителя
        int labourcost = 8;                                         //трудозатраты
        LocalDate executionDate = LocalDate.now().plusDays(2);      //дата исполнения

        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(id);
        dto.setCode(code);
        dto.setName(taskName);
        dto.setDetails(taskDescription);
        dto.setEmployee(empid);
        dto.setLaborCost(labourcost);
        dto.setExecutionDate(executionDate);
        return dto;
    }
}
