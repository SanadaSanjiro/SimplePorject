package com.digdes.simple.test.model.project;

import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.project.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectModelTest {
    @Test
    @DisplayName("ProjectModel setters and getters are ok")
    public void projectModel_CheckSettersGetters() {
        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String prjName = "project001";
        String description = "some project";
        ProjectStatus status = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(prjName);
        projectModel.setDescription(description);
        projectModel.setStatus(status);

        Assertions.assertEquals(code, projectModel.getCode());
        Assertions.assertEquals(prjName, projectModel.getName());
        Assertions.assertEquals(description, projectModel.getDescription());
        Assertions.assertEquals(status, projectModel.getStatus());
    }
}
