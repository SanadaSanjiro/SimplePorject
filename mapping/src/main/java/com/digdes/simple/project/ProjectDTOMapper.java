package com.digdes.simple.project;

import org.springframework.util.ObjectUtils;

//Класс для преобразования модели в ДТО для возврата в качестве результатов запроса
public class ProjectDTOMapper {
    public static ProjectDTO map (ProjectModel model) {
        ProjectDTO dto = new ProjectDTO();
        if (!ObjectUtils.isEmpty(model.getCode())) {
            dto.setCode(model.getCode());
        }
        if (!ObjectUtils.isEmpty(model.getName())) {
            dto.setName(model.getName());
        }
        if (!ObjectUtils.isEmpty(model.getDescription())) {
            dto.setDescription(model.getDescription());
        }
        if (!ObjectUtils.isEmpty(model.getStatus())) {
            dto.setStatus(model.getStatus().toString());
        }
        return dto;
    }
}
