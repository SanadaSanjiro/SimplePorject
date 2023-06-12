package com.digdes.simple.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.digdes.simple.project.ProjectModel;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel, Long>,
        JpaSpecificationExecutor<TaskModel> {
    Optional<List<TaskModel>> getByProject(ProjectModel project);
}
