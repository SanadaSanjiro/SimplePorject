package com.digdes.simple.test.dto.task;

import com.digdes.simple.task.TaskCrtDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskCrtDTOTest {
    @Test
    @DisplayName("TaskDTO setters and getters are ok")
    public void memberDTO_CheckSettersGetters() {
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

        Assertions.assertEquals(code, dto.getCode());
        Assertions.assertEquals(taskName, dto.getName());
        Assertions.assertEquals(taskDescription, dto.getDetails());
        Assertions.assertEquals(empid, dto.getEmployee());
        Assertions.assertEquals(labourcost, dto.getLaborCost());
        Assertions.assertEquals(executionDate, dto.getExecutionDate());
    }
}
