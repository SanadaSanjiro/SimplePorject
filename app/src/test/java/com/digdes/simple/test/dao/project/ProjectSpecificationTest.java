package com.digdes.simple.test.dao.project;

import com.digdes.simple.employee.EmployeeSpecification;
import com.digdes.simple.employee.EmployeeSrchDTO;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.project.ProjectSpecification;
import com.digdes.simple.project.ProjectSrchDTO;
import com.digdes.simple.project.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectSpecificationTest {
    @Test
    @DisplayName("Test Project Specification with non-empty DTO")
    public void getFiltersProject_NonEmptyDTO() {
        String code = "001";
        String prjName = "project001";
        ProjectStatus status = ProjectStatus.DRAFT;
        ProjectSrchDTO dto = new ProjectSrchDTO();
        dto.setCode(code);
        dto.setName(prjName);
        dto.setStatus(status.toString());
        Assertions.assertNotNull(ProjectSpecification.getFilters(dto));
    }

    @Test
    @DisplayName("Test Project Specification with empty DTO")
    public void getFiltersEmployee_EmptyDTO() {
        ProjectSrchDTO dto = new ProjectSrchDTO();
        Assertions.assertNotNull(ProjectSpecification.getFilters(dto));
    }
}
