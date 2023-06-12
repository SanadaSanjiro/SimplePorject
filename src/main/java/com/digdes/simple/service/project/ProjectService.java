package com.digdes.simple.service.project;

import com.digdes.simple.dto.project.*;

import java.util.List;

public interface ProjectService {
    ProjectDTO getByCode(String code);

    ProjectDTO create(ProjectCrtDTO dto);

    ProjectDTO update(ProjectCrtDTO dto);

    ProjectDTO changeStatus(String code);

    List<ProjectDTO> getFiltered(ProjectSrchDTO dto);

    List<ProjectDTO> getAll();
}
