package com.digdes.simple.dao.task;

import com.digdes.simple.dao.employee.EmployeeRepository;
import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.project.ProjectModel;
import com.digdes.simple.model.task.TaskModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskDAO {

    @Autowired
    private TaskRepository taskRepository;

    public TaskModel create(TaskModel taskModel) {
        try {
            return taskRepository.save(taskModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TaskModel update(TaskModel taskModel) {
        try {
            return taskRepository.save(taskModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TaskModel changeStatus (TaskModel taskModel) {
        try {
            return taskRepository.save(taskModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TaskModel> getFilterd() {
        List<TaskModel> list = new ArrayList<>();
        return list;
    }
}
