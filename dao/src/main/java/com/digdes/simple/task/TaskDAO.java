package com.digdes.simple.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.digdes.simple.project.ProjectModel;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskDAO {

    @Autowired
    private TaskRepository taskRepository;

    public TaskModel getById(Long id) {
        try {
            Optional<TaskModel> optional= taskRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public TaskModel create(TaskModel taskModel) {
        try {
            return taskRepository.save(taskModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public TaskModel update(TaskModel taskModel) {
        try {
            return taskRepository.save(taskModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public TaskModel changeStatus (TaskModel taskModel) {
        try {
            return taskRepository.save(taskModel);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public List<TaskModel> getFiltered(TaskSrchDTO dto) {
        try {
            return taskRepository.findAll(TaskSpecification.getFilters(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TaskModel> getByProject(ProjectModel projectModel) {
        try {
            Optional<List<TaskModel>> optional = taskRepository.getByProject(projectModel);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }
}
