package com.digdes.simple.dao.task;

import com.digdes.simple.model.task.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {

}
