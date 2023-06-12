package com.digdes.simple.test.web;

import com.digdes.simple.ProjectController;
import com.digdes.simple.project.*;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProjectControllerTest extends BaseTest {
    @Mock
    ProjectService service;

    @InjectMocks
    ProjectController controller;

    @Test
    @DisplayName("Get Project by code with a controller AllGoodConditions")
    public void getByCodeProject_AllGoodConditions() {
        String code = "001";
        String name = "project01";
        String descr = "some project";
        String status = ProjectStatus.DRAFT.toString();
        ProjectDTO dto = new ProjectDTO();
        dto.setCode(code);
        dto.setName(name);
        dto.setDescription(descr);
        dto.setStatus(status);

        when(service.getByCode(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.getByCode(code));
        verify(service, Mockito.times(1)).getByCode(Mockito.any());
    }

    @Test
    @DisplayName("Create Project with a controller AllGoodConditions")
    public void createProject_AllGoodConditions() {
        String code = "001";
        String name = "project01";
        String descr = "some project";
        String status = ProjectStatus.DRAFT.toString();

        ProjectDTO dto = new ProjectDTO();
        dto.setCode(code);
        dto.setName(name);
        dto.setDescription(descr);
        dto.setStatus(status);

        ProjectCrtDTO crtDTO = new ProjectCrtDTO();
        crtDTO.setCode(code);
        crtDTO.setName(name);
        crtDTO.setDescription(descr);

        when(service.create(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.create(crtDTO));
        verify(service, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    @DisplayName("Update Project with a controller AllGoodConditions")
    public void updateProject_AllGoodConditions() {
        String code = "001";
        String name = "project01";
        String descr = "some project";
        String status = ProjectStatus.DRAFT.toString();

        ProjectDTO dto = new ProjectDTO();
        dto.setCode(code);
        dto.setName(name);
        dto.setDescription(descr);
        dto.setStatus(status);

        ProjectCrtDTO crtDTO = new ProjectCrtDTO();
        crtDTO.setCode(code);
        crtDTO.setName(name);
        crtDTO.setDescription(descr);

        when(service.update(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.update(crtDTO));
        verify(service, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    @DisplayName("Change Project Status by code with a controller AllGoodConditions")
    public void changeStatusProject_AllGoodConditions() {
        String code = "001";
        String name = "project01";
        String descr = "some project";
        String status = ProjectStatus.DRAFT.toString();
        ProjectDTO dto = new ProjectDTO();
        dto.setCode(code);
        dto.setName(name);
        dto.setDescription(descr);
        dto.setStatus(status);

        when(service.changeStatus(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.changeStatus(code));
        verify(service, Mockito.times(1)).changeStatus(Mockito.any());
    }

    @Test
    @DisplayName("GetFiltered Project with a controller AllGoodConditions")
    public void getFilteredProject_AllGoodConditions() {
        String code = "001";
        String name = "project01";
        String descr = "some project";
        String status = ProjectStatus.DRAFT.toString();
        ProjectDTO dto = new ProjectDTO();
        dto.setCode(code);
        dto.setName(name);
        dto.setDescription(descr);
        dto.setStatus(status);
        List<ProjectDTO> list = new ArrayList<>();
        list.add(dto);

        ProjectSrchDTO srchDTO = new ProjectSrchDTO();
        srchDTO.setName(name);

        when(service.getFiltered(any())).thenReturn(list);
        Assertions.assertFalse(controller.getFiltered(srchDTO).isEmpty());
        verify(service, Mockito.times(1)).getFiltered(Mockito.any());
    }

    @Test
    @DisplayName("GetAll Project with a controller AllGoodConditions")
    public void getAlldProject_AllGoodConditions() {
        String code = "001";
        String name = "project01";
        String descr = "some project";
        String status = ProjectStatus.DRAFT.toString();
        ProjectDTO dto = new ProjectDTO();
        dto.setCode(code);
        dto.setName(name);
        dto.setDescription(descr);
        dto.setStatus(status);
        List<ProjectDTO> list = new ArrayList<>();
        list.add(dto);

        when(service.getAll()).thenReturn(list);
        Assertions.assertFalse(controller.getAll().isEmpty());
        verify(service, Mockito.times(1)).getAll();
    }
}
