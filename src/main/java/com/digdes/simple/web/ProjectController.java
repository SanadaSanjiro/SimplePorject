package com.digdes.simple.web;

import com.digdes.simple.dto.project.ProjectCrtDTO;
import com.digdes.simple.dto.project.ProjectDTO;
import com.digdes.simple.dto.project.ProjectSrchDTO;
import com.digdes.simple.service.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Tag(name= "ProjectController", description = "Контроллер проектов")
public class ProjectController {
    private final ProjectService projectService;

    @Operation(summary = "Получить ионформацию о проекте по коду")
    @GetMapping(value = "/get/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDTO getByCode(@PathVariable String code) {
        return projectService.getByCode(code);
    }

    @Operation(summary = "Создать новый проект")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDTO create(@RequestBody ProjectCrtDTO dto) {
        return projectService.create(dto);
    }

    @Operation(summary = "Изменить данные проекта. Незаполненные поля обнуляются!")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDTO update(@RequestBody ProjectCrtDTO dto) {
        return projectService.update(dto);
    }

    @Operation(summary = "Изменить статус проекта. Статусы: Черновик->В разработке->В тестировании->Завершен")
    @PutMapping(value = "/changestatus/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDTO changeStatus(@PathVariable String code) {
        return projectService.changeStatus(code);
    }

    @Operation(summary = "Отфильтровать проекты по значениям полей")
    @PutMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDTO> getFiltered(@RequestBody ProjectSrchDTO dto) {
        return projectService.getFiltered(dto);
    }

    @Operation(summary = "Получить список всех проектов")
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDTO> getAll() {
        return projectService.getAll();
    }
}