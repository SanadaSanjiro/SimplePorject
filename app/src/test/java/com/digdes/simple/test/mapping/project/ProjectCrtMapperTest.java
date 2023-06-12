package com.digdes.simple.test.mapping.project;

import com.digdes.simple.project.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectCrtMapperTest {
    @Test
    @DisplayName("ProjectCrtMapper maps all fields DTO->model")
    public void test () {
        ProjectCrtDTO dto = new ProjectCrtDTO();
        String code = "001";
        String prjName = "project001";
        String description = "some project";
        dto.setCode(code);
        dto.setName(prjName);
        dto.setDescription(description);

        ProjectModel model = ProjectCrtMapper.map(dto);
        Assertions.assertEquals(code, model.getCode());
        Assertions.assertEquals(prjName, model.getName());
        Assertions.assertEquals(description, model.getDescription());
    }
}
