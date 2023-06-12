package com.digdes.simple.service.task;

import com.digdes.simple.dto.task.TaskCrtDTO;
import com.digdes.simple.dto.task.TaskDTO;
import com.digdes.simple.dto.task.TaskSrchDTO;
import com.digdes.simple.dto.task.TaskUpdDTO;

import java.util.List;

public interface TaskService {

    TaskDTO getById(Long id);

    TaskDTO create(TaskCrtDTO dto);

    TaskDTO update(TaskUpdDTO dto);

    List<TaskDTO> getFiltered(TaskSrchDTO dto);

    TaskDTO changeStatus(Long id);

    List<TaskDTO> getByPrjCode(String code);
}
