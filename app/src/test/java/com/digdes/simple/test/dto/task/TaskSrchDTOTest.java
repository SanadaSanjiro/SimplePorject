package com.digdes.simple.test.dto.task;

import com.digdes.simple.task.TaskSrchDTO;
import com.digdes.simple.task.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskSrchDTOTest {
    @Test
    @DisplayName("TaskDTO setters and getters are ok")
    public void memberDTO_CheckSettersGetters() {
        String taskName = "task01";                                 //наименование задачи
        Long empid = 1L;                                            //id исполнителя
        LocalDate executionDate = LocalDate.now().plusDays(2);      //дата исполнения
        TaskStatus taskStatus = TaskStatus.NEW;                     //статус
        Long authId = 2L;                                           //id автора задачи
        LocalDate creationDate = LocalDate.now();                   //дата создания

        TaskSrchDTO dto = new TaskSrchDTO();
        dto.setName(taskName);
        dto.setEmployee(empid);
        dto.setExecutionDate(executionDate);
        dto.setStatus(taskStatus.toString());
        dto.setAuthor(authId);
        dto.setCreationDate(creationDate);

        Assertions.assertEquals(taskName, dto.getName());
        Assertions.assertEquals(empid, dto.getEmployee());
        Assertions.assertEquals(executionDate, dto.getExecutionDate());
        Assertions.assertEquals(taskStatus.toString(), dto.getStatus());
        Assertions.assertEquals(authId, dto.getAuthor());
        Assertions.assertEquals(creationDate, dto.getCreationDate());
    }
}
