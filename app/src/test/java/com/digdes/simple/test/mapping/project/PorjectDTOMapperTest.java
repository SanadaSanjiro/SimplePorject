package com.digdes.simple.test.mapping.project;

import com.digdes.simple.project.ProjectDTO;
import com.digdes.simple.project.ProjectDTOMapper;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.project.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PorjectDTOMapperTest {
    @Test
    @DisplayName("ProjectMapper maps all fields model->DTO")
    public void test () {
        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String prjName = "project001";
        String description = "some project";
        ProjectStatus status = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(prjName);
        projectModel.setDescription(description);
        projectModel.setStatus(status);

        ProjectDTO dto = ProjectDTOMapper.map(projectModel);
        Assertions.assertEquals(code, dto.getCode());
        Assertions.assertEquals(prjName, dto.getName());
        Assertions.assertEquals(description, dto.getDescription());
        Assertions.assertEquals(status.toString(), dto.getStatus());
    }
}
