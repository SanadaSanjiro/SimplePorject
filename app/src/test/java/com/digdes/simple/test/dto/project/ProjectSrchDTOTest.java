package com.digdes.simple.test.dto.project;

import com.digdes.simple.project.ProjectSrchDTO;
import com.digdes.simple.project.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectSrchDTOTest {
    @Test
    @DisplayName("ProjectDTO setters and getters are ok")
    public void memberDTO_CheckSettersGetters() {
        String code = "001";
        String prjName = "project001";
        ProjectStatus status = ProjectStatus.DRAFT;
        ProjectSrchDTO dto = new ProjectSrchDTO();
        dto.setCode(code);
        dto.setName(prjName);
        dto.setStatus(status.toString());
        Assertions.assertEquals(code, dto.getCode());
        Assertions.assertEquals(prjName, dto.getName());
        Assertions.assertEquals(status.toString(), dto.getStatus());
    }
}
