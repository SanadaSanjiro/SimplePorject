package com.digdes.simple.mapping.project;

import com.digdes.simple.dto.project.ProjectCrtDTO;
import com.digdes.simple.model.project.ProjectModel;
import org.springframework.util.ObjectUtils;

//Класс для преобразования ДТО в модель при создании/изменении проекта
public class ProjectCrtMapper {
    public static ProjectModel map (ProjectCrtDTO dto) {
        ProjectModel model = new ProjectModel();
        if (!ObjectUtils.isEmpty(dto.getCode())) {
            model.setCode(dto.getCode());
        }
        if (!ObjectUtils.isEmpty(dto.getName())) {
            model.setName(dto.getName());
        }
        if (!ObjectUtils.isEmpty(dto.getDescription())) {
            model.setDescription(dto.getDescription());
        }
        return model;
    }
}
