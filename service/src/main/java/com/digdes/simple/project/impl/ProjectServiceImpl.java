package com.digdes.simple.project.impl;

import com.digdes.simple.project.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private final ProjectDAO projectDAO;

    @Override
    public ProjectDTO getByCode(String code) {
        if (code==null) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
        ProjectModel model = projectDAO.getByCode(code);
        if (model==null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        return ProjectDTOMapper.map(model);
    }

    @Override
    public ProjectDTO create(ProjectCrtDTO dto) {
        if (dto == null || dto.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (projectDAO.getByCode(dto.getCode())!=null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ProjectModel model = ProjectCrtMapper.map(dto);
        model.setStatus(ProjectStatus.DRAFT);
        model = projectDAO.create(model);
        return ProjectDTOMapper.map(model);
    }

    @Override
    public ProjectDTO update(ProjectCrtDTO dto) {
        if (dto == null || dto.getName() == null || dto.getCode() ==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if ((projectDAO.getByCode(dto.getCode())==null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ProjectModel model = ProjectCrtMapper.map(dto);
        ProjectStatus status = projectDAO.getByCode(dto.getCode()).getStatus();
        model.setStatus(status);
        model = projectDAO.update(model);
        return ProjectDTOMapper.map(model);
    }

    @Override
    public ProjectDTO changeStatus(String code) {
        if (code == null || projectDAO.getByCode(code) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ProjectModel model = projectDAO.getByCode(code);
        ProjectStatus status = model.getStatus();
        switch (status) {
            case DRAFT: {
                status = ProjectStatus.DEVELOPING;
                break;
            }
            case DEVELOPING: {
                status = ProjectStatus.TESTING;
                break;
            }
            case TESTING: {
                status = ProjectStatus.COMPLETE;
                break;
            }
        }
        model.setStatus(status);
        model = projectDAO.changeStatus(model);
        return ProjectDTOMapper.map(model);
    }

    @Override
    public List<ProjectDTO> getFiltered(ProjectSrchDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<ProjectDTO> dtos = projectDAO.getFiltered(dto)
                .stream()
                .map(m-> ProjectDTOMapper.map(m))
                .toList();
        return dtos;
    }

    @Override
    public List<ProjectDTO> getAll() {
        List<ProjectDTO> dtos = projectDAO.getAll()
                .stream()
                .map(m-> ProjectDTOMapper.map(m))
                .toList();
        return dtos;
    }
}
