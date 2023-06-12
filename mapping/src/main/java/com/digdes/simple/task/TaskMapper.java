package com.digdes.simple.task;

import org.springframework.util.ObjectUtils;

public class TaskMapper {

    public static TaskDTO map (TaskModel model) {
        TaskDTO dto = new TaskDTO();
        if (!ObjectUtils.isEmpty(model.getId())) {
            dto.setId(model.getId());
        }
        if (!ObjectUtils.isEmpty(model.getProject())) {
            dto.setCode(model.getProject().getCode());
        }
        if (!ObjectUtils.isEmpty(model.getName())) {
            dto.setName(model.getName());
        }
        if (!ObjectUtils.isEmpty(model.getDetails())) {
            dto.setDetails(model.getDetails());
        }
        if (!ObjectUtils.isEmpty(model.getEmployee())) {
            dto.setEmployee(model.getEmployee().getId());
        }
        if (!ObjectUtils.isEmpty(model.getLaborCost())) {
            dto.setLaborCost(model.getLaborCost());
        }
        if (!ObjectUtils.isEmpty(model.getExecutiondate())) {
            dto.setExecutionDate(model.getExecutiondate());
        }
        if (!ObjectUtils.isEmpty(model.getAuthor())) {
            dto.setAuthor(model.getAuthor().getId());
        }
        if (!ObjectUtils.isEmpty(model.getCreationdate())) {
            dto.setCreationDate(model.getCreationdate());
        }
        if (!ObjectUtils.isEmpty(model.getChangedate())) {
            dto.setChangeDate(model.getChangedate());
        }
        if (!ObjectUtils.isEmpty(model.getStatus())) {
            dto.setStatus(model.getStatus().toString());
        }
        return dto;
    }
}
