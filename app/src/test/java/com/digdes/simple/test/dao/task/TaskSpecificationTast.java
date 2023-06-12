package com.digdes.simple.test.dao.task;

import com.digdes.simple.task.TaskSpecification;
import com.digdes.simple.task.TaskSrchDTO;
import com.digdes.simple.task.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskSpecificationTast {
    @Test
    @DisplayName("Test Task Specification with non-empty DTO")
    public void getFiltersTask_NonEmptyDTO() {
        String name = "task01";
        TaskStatus status = TaskStatus.NEW;
        TaskSrchDTO dto = new TaskSrchDTO();
        dto.setName(name);
        dto.setStatus(status.toString());
        Assertions.assertNotNull(TaskSpecification.getFilters(dto));
    }

    @Test
    @DisplayName("Test Task Specification with empty DTO")
    public void getFiltersEmployee_EmptyDTO() {
        TaskSrchDTO dto = new TaskSrchDTO();
        Assertions.assertNotNull(TaskSpecification.getFilters(dto));
    }
}
