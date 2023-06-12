package com.digdes.simple.dao.project;

import com.digdes.simple.model.project.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectModel, String> {

}
