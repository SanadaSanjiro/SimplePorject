package com.digdes.simple.test.dto.task;

import com.digdes.simple.task.TaskDTO;
import com.digdes.simple.task.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskDTOTest {
    @Test
    @DisplayName("TaskDTO setters and getters are ok")
    public void memberDTO_CheckSettersGetters() {
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

        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(code, dto.getCode());
        Assertions.assertEquals(taskName, dto.getName());
        Assertions.assertEquals(taskDescription, dto.getDetails());
        Assertions.assertEquals(empid, dto.getEmployee());
        Assertions.assertEquals(labourcost, dto.getLaborCost());
        Assertions.assertEquals(executionDate, dto.getExecutionDate());
        Assertions.assertEquals(taskStatus.toString(), dto.getStatus());
        Assertions.assertEquals(authId, dto.getAuthor());
        Assertions.assertEquals(creationDate, dto.getCreationDate());
        Assertions.assertEquals(changeDate, dto.getChangeDate());
    }
}
