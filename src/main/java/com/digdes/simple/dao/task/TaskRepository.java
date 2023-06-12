package com.digdes.simple.dao.task;

import com.digdes.simple.model.project.ProjectModel;
import com.digdes.simple.model.task.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel, Long>,
        JpaSpecificationExecutor<TaskModel> {
    Optional<List<TaskModel>> getByProject(ProjectModel project);
}
