package com.digdes.simple.test.dto.project;

import com.digdes.simple.project.ProjectDTO;
import com.digdes.simple.project.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectDTOTest {
    @Test
    @DisplayName("ProjectDTO setters and getters are ok")
    public void memberDTO_CheckSettersGetters() {
        String code = "001";
        String prjName = "project001";
        String description = "some_project";
        ProjectStatus status = ProjectStatus.DRAFT;
        ProjectDTO dto = new ProjectDTO();
        dto.setCode(code);
        dto.setName(prjName);
        dto.setStatus(status.toString());
        dto.setDescription(description);
        Assertions.assertEquals(code, dto.getCode());
        Assertions.assertEquals(prjName, dto.getName());
        Assertions.assertEquals(description, dto.getDescription());
        Assertions.assertEquals(status.toString(), dto.getStatus());
    }
}
