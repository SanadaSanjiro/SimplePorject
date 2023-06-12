package com.digdes.simple.dao.project;

import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.project.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectModel, String>,
        JpaSpecificationExecutor<ProjectModel> {
    Optional<ProjectModel> getByCode(String code);
}
