package com.digdes.simple;

import com.digdes.simple.project.ProjectCrtDTO;
import com.digdes.simple.project.ProjectDTO;
import com.digdes.simple.project.ProjectService;
import com.digdes.simple.project.ProjectSrchDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Tag(name= "com.digdes.simple.ProjectController", description = "Контроллер проектов")
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

    @Operation(summary = "Прикрепить к проекту файл")
    @PostMapping(value = "/upload/{code}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDTO addFile(@RequestParam MultipartFile attachment, @PathVariable String code) {
        try {
            return projectService.addFile(code, attachment);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}