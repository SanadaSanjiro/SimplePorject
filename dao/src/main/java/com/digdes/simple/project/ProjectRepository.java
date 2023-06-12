package com.digdes.simple.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectModel, String>,
        JpaSpecificationExecutor<ProjectModel> {
    Optional<ProjectModel> getByCode(String code);
}
