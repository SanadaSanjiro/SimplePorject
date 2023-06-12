package com.digdes.simple.web;

import com.digdes.simple.dto.member.MemberDTO;
import com.digdes.simple.dto.project.ProjectDTO;
import com.digdes.simple.dto.project.ProjectSrchDTO;
import com.digdes.simple.dto.task.TaskCrtDTO;
import com.digdes.simple.dto.task.TaskDTO;
import com.digdes.simple.dto.task.TaskSrchDTO;
import com.digdes.simple.dto.task.TaskUpdDTO;
import com.digdes.simple.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Tag(name= "TaskController", description = "Контроллер задач")
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Создать новую задачу")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO create(@RequestBody TaskCrtDTO dto) {
        return taskService.create(dto);
    }

    @Operation(summary = "Получить задачу по id")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @Operation(summary = "Изменить задачу. Незаполненные поля обнуляются!")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO update(@RequestBody TaskUpdDTO dto) {
        return taskService.update(dto);
    }

    @Operation(summary = "Изменить статус задачи. Статусы: Новая->В рабооте->Выполнена->Закрыта")
    @PutMapping(value = "/changestatus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO changeStatus(@PathVariable Long id) {
        return taskService.changeStatus(id);
    }

    @Operation(summary = "Получить список задач по коду проекта")
    @GetMapping(value = "/get/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> getByPrjCode(@RequestParam(value="code") String code) {
        return taskService.getByPrjCode(code);
    }

    @Operation(summary = "Отфильтровать задачи по значениям полей")
    @PutMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> getFiltered(@RequestBody TaskSrchDTO dto) {
        return taskService.getFiltered(dto);
    }
}
