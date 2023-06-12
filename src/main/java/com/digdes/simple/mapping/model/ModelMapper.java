package com.digdes.simple.mapping.model;

import com.digdes.simple.dto.task.TaskDTO;
import com.digdes.simple.model.task.TaskModel;
import com.digdes.simple.model.task.TaskStatus;
import org.springframework.util.ObjectUtils;

public class ModelMapper {
    public static TaskModel map (TaskDTO dto) {
        TaskModel model = new TaskModel();
        if (!ObjectUtils.isEmpty(dto.getId())) {
            model.setId(dto.getId());
        }
        if (!ObjectUtils.isEmpty(dto.getName())) {
            model.setName(dto.getName());
        }
        if (!ObjectUtils.isEmpty(dto.getDetails())) {
            model.setDetails(dto.getDetails());
        }
        if (!ObjectUtils.isEmpty(dto.getEmployee())) {
            //разобраться что тут ставить
            //model.setEmployee(dto.getEmployee());
        }
        if (!ObjectUtils.isEmpty(dto.getLaborCost())) {
            model.setLaborCost(dto.getLaborCost());
        }
        if (!ObjectUtils.isEmpty(dto.getExecutionDate())) {
            model.setExecutionDate(dto.getExecutionDate());
        }
        if (!ObjectUtils.isEmpty(dto.getAuthor())) {
            //разобраться что тут ставить
            // model.serAuthor(dto.getAuthor());
        }
        if (!ObjectUtils.isEmpty(dto.getCreationDate())) {
            model.setCreationDate(dto.getCreationDate());
        }
        if (!ObjectUtils.isEmpty(dto.getChangeDate())) {
            model.setCreationDate(dto.getChangeDate());
        }
        if (!ObjectUtils.isEmpty(dto.getStatus())) {
            switch (dto.getStatus()) {
                case ("NEW"):
                    model.setStatus(TaskStatus.NEW);
                    break;
                case ("PROCESSED"):
                    model.setStatus(TaskStatus.PROCESSED);
                    break;
                case ("COMPLETE"):
                    model.setStatus(TaskStatus.COMPLETE);
                    break;
                case ("CLOSED"):
                    model.setStatus(TaskStatus.CLOSED);
                    break;
            }
        }
        return model;
    }

    public static TaskDTO map (TaskModel model) {
        TaskDTO dto = new TaskDTO();
        if (!ObjectUtils.isEmpty(model.getId())) {
            dto.setId(model.getId());
        }
        if (!ObjectUtils.isEmpty(model.getName())) {
            dto.setName(model.getName());
        }
        if (!ObjectUtils.isEmpty(model.getDetails())) {
            dto.setDetails(model.getDetails());
        }
        if (!ObjectUtils.isEmpty(model.getEmployee())) {
            //разобраться что тут ставить
            //dto.setEmployee(dto.getEmployee());
        }
        if (!ObjectUtils.isEmpty(model.getLaborCost())) {
            dto.setLaborCost(model.getLaborCost());
        }
        if (!ObjectUtils.isEmpty(model.getExecutionDate())) {
            dto.setExecutionDate(model.getExecutionDate());
        }
        if (!ObjectUtils.isEmpty(model.getAuthor())) {
            //разобраться что тут ставить
            // dto.serAuthor(model.getAuthor());
        }
        if (!ObjectUtils.isEmpty(model.getCreationDate())) {
            dto.setCreationDate(model.getCreationDate());
        }
        if (!ObjectUtils.isEmpty(model.getChangeDate())) {
            dto.setCreationDate(model.getChangeDate());
        }
        if (!ObjectUtils.isEmpty(model.getStatus())) {
            switch (model.getStatus().name()) {
                case ("NEW") :
                    dto.setStatus("NEW");
                    break;
                case ("PROCESSED"):
                    dto.setStatus("PROCESSED");
                    break;
                case ("COMPLETE"):
                    dto.setStatus("COMPLETE");
                    break;
                case ("CLOSED"):
                    dto.setStatus("CLOSED");
                    break;
            }
        }
        return dto;
    }
}
