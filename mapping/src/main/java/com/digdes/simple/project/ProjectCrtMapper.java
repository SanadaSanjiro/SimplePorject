package com.digdes.simple.project;

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
