package com.digdes.simple.task;

import java.util.List;

public interface TaskService {

    TaskDTO getById(Long id);

    TaskDTO create(TaskCrtDTO dto);

    TaskDTO update(TaskUpdDTO dto);

    List<TaskDTO> getFiltered(TaskSrchDTO dto);

    TaskDTO changeStatus(Long id);

    List<TaskDTO> getByPrjCode(String code);
}
