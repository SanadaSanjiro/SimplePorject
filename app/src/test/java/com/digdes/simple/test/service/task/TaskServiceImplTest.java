package com.digdes.simple.test.service.task;

import com.digdes.simple.employee.EmployeeDAO;
import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.project.ProjectDAO;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.project.ProjectStatus;
import com.digdes.simple.task.*;
import com.digdes.simple.task.impl.TaskServiceImpl;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceImplTest extends BaseTest {
    @Mock
    TaskDAO taskDAO;

    @Mock
    EmployeeDAO employeeDAO;

    @Mock
    ProjectDAO projectDAO;

    @Mock
    Authentication auth;

    @InjectMocks
    TaskServiceImpl taskService;

    //Настраиваем мок секьюрити контекста для тестов
    @BeforeEach
    public void initSecurityContext() {
        when(auth.getName()).thenReturn("Author");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    public void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    /****************************************************************************
     * Tests of creation
     */

    @Test
    @DisplayName("Create Task when all conditions ok")
    public void createTask_AllGoodConditions() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        //Создаем ДТО из которого собирается объект Задачи
        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(model.getExecutiondate());
        dto.setLaborCost(model.getLaborCost());

        //Создаем модель автора
        EmployeeModel authorModel = createAuthor();

        //Настраиваем правильные реакции моков
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        //Анонимный класс, получающий результат работы тестируемого метода, добавляющий id и возвращающий его обратно
        when(taskDAO.create(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setId(model.getId());
                return tm;
            }
        });

        //Вызываем метод create и проверяем правильность результатов
        TaskDTO result = taskService.create(dto);
        //System.out.println(result);
        Assertions.assertEquals(model.getId(), result.getId());
        Assertions.assertEquals(model.getProject().getCode(), result.getCode());
        Assertions.assertEquals(model.getName(), result.getName());
        Assertions.assertEquals(model.getDetails(), result.getDetails());
        Assertions.assertEquals(model.getEmployee().getId(), result.getEmployee());
        Assertions.assertEquals(model.getLaborCost(), result.getLaborCost());
        Assertions.assertEquals(model.getExecutiondate(), result.getExecutionDate());
        Assertions.assertEquals(TaskStatus.NEW.toString(), result.getStatus());
        Assertions.assertEquals(authorModel.getId(), result.getAuthor());
        Assertions.assertEquals(LocalDate.now(), result.getCreationDate());
        Assertions.assertEquals(LocalDate.now(), result.getChangeDate());
    }

    @Test
    @DisplayName("Create Task empty DTO throws exception")
    public void createTask_emptyDTO_ThrowException() {
        TaskCrtDTO dto = new TaskCrtDTO();
        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }

    @Test
    @DisplayName("Create Task no Task name throws exception")
    public void createTask_NoTaskName_ThrowException() {
        TaskModel model = createTaskModel();

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(model.getExecutiondate());
        dto.setLaborCost(model.getLaborCost());
        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }

    @Test
    @DisplayName("Create Task no Project code throws exception")
    public void createTask_NoProjectCode_ThrowException() {
        TaskModel model = createTaskModel();

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(model.getExecutiondate());
        dto.setLaborCost(model.getLaborCost());
        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }

    @Test
    @DisplayName("Create Task no Labour Cost throws exception")
    public void createTask_NoLaborCost_ThrowException() {
        TaskModel model = createTaskModel();

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(model.getExecutiondate());
        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }

    @Test
    @DisplayName("Create Task no Execution Date throws exception")
    public void createTask_NoExecutionDate_ThrowException() {
        TaskModel model = createTaskModel();

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setLaborCost(model.getLaborCost());
        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }

    @Test
    @DisplayName("Create Task no such Project throws exception")
    public void createTask_NoSuchProject_ThrowException() {
        TaskModel model = createTaskModel();
        EmployeeModel employeeModel = model.getEmployee();

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(model.getExecutiondate());
        dto.setLaborCost(model.getLaborCost());

        EmployeeModel authorModel = createAuthor();

        when(projectDAO.getByCode(any())).thenReturn(null);
        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.create(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setId(model.getId());
                return tm;
            }
        });

        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }

    @Test
    @DisplayName("Create Task no such Employee throws exception")
    public void createTask_NoSuchEmployee_ThrowException() {
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(model.getExecutiondate());
        dto.setLaborCost(model.getLaborCost());

        EmployeeModel authorModel = createAuthor();

        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(employeeDAO.getById(any())).thenReturn(null);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.create(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setId(model.getId());
                return tm;
            }
        });

        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }

    @Test
    @DisplayName("Create Task but Execution Date too short throws exception")
    public void createTask_BadExecutionDate_ThrowException() {
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        model.setLaborCost(200);

        TaskCrtDTO dto = new TaskCrtDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(model.getExecutiondate());
        dto.setLaborCost(model.getLaborCost());

        EmployeeModel authorModel = createAuthor();

        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.create(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setId(model.getId());
                return tm;
            }
        });

        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.create(dto));
    }
    /****************************************************************************
     * GetByID tests
     */
    @Test
    @DisplayName("GetById Task when all conditions ok")
    public void getByIdTask_AllGoodConditions() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        Long id = model.getId();

        when(taskDAO.getById(any())).thenReturn(model);

        TaskDTO result = taskService.getById(id);
        Assertions.assertEquals(model.getId(), result.getId());
        Assertions.assertEquals(model.getProject().getCode(), result.getCode());
        Assertions.assertEquals(model.getName(), result.getName());
        Assertions.assertEquals(model.getDetails(), result.getDetails());
        Assertions.assertEquals(model.getEmployee().getId(), result.getEmployee());
        Assertions.assertEquals(model.getLaborCost(), result.getLaborCost());
        Assertions.assertEquals(model.getExecutiondate(), result.getExecutionDate());
        Assertions.assertEquals(model.getStatus().toString(), result.getStatus());
        Assertions.assertEquals(model.getAuthor().getId(), result.getAuthor());
        Assertions.assertEquals(model.getCreationdate(), result.getCreationDate());
        Assertions.assertEquals(model.getChangedate(), result.getChangeDate());
    }

    @Test
    @DisplayName("GetById Task id null throws Exception")
    public void getByIdTask_NoId_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.getById(null));
    }

    @Test
    @DisplayName("GetById Task no result throws Exception")
    public void getByIdTask_NoResult_ThrowException() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        Long id = model.getId();

        when(taskDAO.getById(any())).thenReturn(null);
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.getById(id));
    }

    /****************************************************************************
     * Update tests
     */
    @Test
    @DisplayName("Update Task when all conditions ok")
    public void updateTask_AllGoodConditions() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        //Создаем ДТО для изменения данных Задачи
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost()+8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);

        //Создаем модель автора
        EmployeeModel authorModel = createAuthor();

        //Настраиваем правильные реакции моков
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.getById(any())).thenReturn(model);
        //Анонимный класс, получающий результат работы тестируемого метода, добавляющий id и возвращающий его обратно
        when(taskDAO.update(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setExecutiondate(newExecutionDate);
                tm.setLaborCost(newLaborCost);
                return tm;
            }
        });

        //Вызываем метод create и проверяем правильность результатов
        TaskDTO result = taskService.update(dto);
        Assertions.assertEquals(dto.getId(), result.getId());
        Assertions.assertEquals(dto.getCode(), result.getCode());
        Assertions.assertEquals(dto.getName(), result.getName());
        Assertions.assertEquals(dto.getDetails(), result.getDetails());
        Assertions.assertEquals(dto.getEmployee(), result.getEmployee());
        Assertions.assertEquals(dto.getLaborCost(), result.getLaborCost());
        Assertions.assertEquals(dto.getExecutionDate(), result.getExecutionDate());
        Assertions.assertEquals(authorModel.getId(), result.getAuthor());
        Assertions.assertEquals(model.getCreationdate(), result.getCreationDate());
        Assertions.assertEquals(LocalDate.now(), result.getChangeDate());
    }

    @Test
    @DisplayName("Update Task empty request throws Exception")
    public void updateTask_EmptyRequest_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(null));
    }

    @Test
    @DisplayName("Update Task empty name throws Exception")
    public void updateTask_EmptyName_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();

        //Создаем ДТО для изменения данных Задачи, не заполняем Наименование задачи
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost() + 8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task empty project code throws Exception")
    public void updateTask_EmptyCode_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();

        //Создаем ДТО для изменения данных Задачи, не заполняем код проекта
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost() + 8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task empty id throws Exception")
    public void updateTask_EmptyId_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();

        //Создаем ДТО для изменения данных Задачи, не заполняем id задачи
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost() + 8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task empty labor cost throws Exception")
    public void updateTask_EmptyLaborCost_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();

        //Создаем ДТО для изменения данных Задачи, не заполняем трудозатраты
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost() + 8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task empty execution date throws Exception")
    public void updateTask_EmptyExecutionDate_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();

        //Создаем ДТО для изменения данных Задачи, не заполняем дату исполнения
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost() + 8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setLaborCost(newLaborCost);
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task no such task throws exception")
    public void updateTask_NoTask_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        //Создаем ДТО для изменения данных Задачи
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost()+8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);

        //Создаем модель автора
        EmployeeModel authorModel = createAuthor();

        //Настраиваем правильные реакции моков, при запросе задачи возвращаем null
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.getById(any())).thenReturn(null);
        //Анонимный класс, получающий результат работы тестируемого метода, добавляющий id и возвращающий его обратно
        when(taskDAO.update(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setExecutiondate(newExecutionDate);
                tm.setLaborCost(newLaborCost);
                return tm;
            }
        });

        //Вызываем метод create и проверяем правильность результатов
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task no such project throws exception")
    public void updateTask_NoProject_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        //Создаем ДТО для изменения данных Задачи
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost()+8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);

        //Создаем модель автора
        EmployeeModel authorModel = createAuthor();

        //Настраиваем правильные реакции моков, при запросе проекта возвращаем null
        when(projectDAO.getByCode(any())).thenReturn(null);
        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.getById(any())).thenReturn(model);
        //Анонимный класс, получающий результат работы тестируемого метода, добавляющий id и возвращающий его обратно
        when(taskDAO.update(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setExecutiondate(newExecutionDate);
                tm.setLaborCost(newLaborCost);
                return tm;
            }
        });

        //Вызываем метод create и проверяем правильность результатов
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task no such employee throws exception")
    public void updateTask_NoEmployee_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        //Создаем ДТО для изменения данных Задачи
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        int newLaborCost = model.getLaborCost()+8;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);

        //Создаем модель автора
        EmployeeModel authorModel = createAuthor();

        //Настраиваем правильные реакции моков, при запросе исполнителя возвращаем null
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(employeeDAO.getById(any())).thenReturn(null);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.getById(any())).thenReturn(model);
        //Анонимный класс, получающий результат работы тестируемого метода, добавляющий id и возвращающий его обратно
        when(taskDAO.update(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setExecutiondate(newExecutionDate);
                tm.setLaborCost(newLaborCost);
                return tm;
            }
        });

        //Вызываем метод create и проверяем правильность результатов
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }

    @Test
    @DisplayName("Update Task execution date too short throws exception")
    public void updateTask_ExecutionDateTooShort_ThrowException() {
        //Создаем модели для возврата моками
        TaskModel model = createTaskModel();
        ProjectModel projectModel = model.getProject();
        EmployeeModel employeeModel = model.getEmployee();

        //Создаем ДТО для изменения данных Задачи
        LocalDate newExecutionDate = model.getExecutiondate().plusDays(2);
        //Устанавливаем трудозатраты намного превышающие срок исполнения
        int newLaborCost = model.getLaborCost()+200;
        TaskUpdDTO dto = new TaskUpdDTO();
        dto.setId(model.getId());
        dto.setCode(model.getProject().getCode());
        dto.setEmployee(model.getEmployee().getId());
        dto.setName(model.getName());
        dto.setDetails(model.getDetails());
        dto.setExecutionDate(newExecutionDate);
        dto.setLaborCost(newLaborCost);

        //Создаем модель автора
        EmployeeModel authorModel = createAuthor();

        //Настраиваем правильные реакции моков
        when(projectDAO.getByCode(any())).thenReturn(projectModel);
        when(employeeDAO.getById(any())).thenReturn(employeeModel);
        when(employeeDAO.getByAccount(any())).thenReturn(authorModel);
        when(taskDAO.getById(any())).thenReturn(model);
        //Анонимный класс, получающий результат работы тестируемого метода, добавляющий id и возвращающий его обратно
        when(taskDAO.update(any(TaskModel.class))).thenAnswer(new Answer<TaskModel>() {
            public TaskModel answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                TaskModel tm = (TaskModel) args[0];
                tm.setExecutiondate(newExecutionDate);
                tm.setLaborCost(newLaborCost);
                return tm;
            }
        });

        //Вызываем метод create и проверяем правильность результатов
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.update(dto));
    }
    /****************************************************************************
     * GetFiltered tests
     */
    @Test
    @DisplayName("GetFiltered Task when all conditions ok")
    public void getFilteredTask_AllGoodConditions() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        TaskSrchDTO dto = new TaskSrchDTO();
        dto.setName(model.getName());
        List<TaskModel> list = new ArrayList<>();
        list.add(model);

        when(taskDAO.getFiltered(any())).thenReturn(list);

        Assertions.assertFalse(taskService.getFiltered(dto).isEmpty());
    }

    @Test
    @DisplayName("GetFiltered Task empty DTO Throws Exception")
    public void getFilteredTask_EmptyDTO_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class,()->taskService.getFiltered(null));
    }

    @Test
    @DisplayName("GetFiltered Task no results")
    public void getFilteredTask_NoResults() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        TaskSrchDTO dto = new TaskSrchDTO();
        dto.setName(model.getName());

        Assertions.assertTrue(taskService.getFiltered(dto).isEmpty());
    }

    /****************************************************************************
     * changeStatus tests
     */
    @Test
    @DisplayName("ChangeStatus Task when all conditions ok")
    public void changeStatusTask_AllGoodConditions() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        Long id = model.getId();

        when(taskDAO.changeStatus(any())).thenReturn(model);
        when(taskDAO.getById(any())).thenReturn(model);

        TaskDTO result = taskService.changeStatus(id);
        Assertions.assertEquals(TaskStatus.PROCESSED.toString(), result.getStatus());
        result = taskService.changeStatus(id);
        Assertions.assertEquals(TaskStatus.COMPLETE.toString(), result.getStatus());
        result = taskService.changeStatus(id);
        Assertions.assertEquals(TaskStatus.CLOSED.toString(), result.getStatus());
        result = taskService.changeStatus(id);
        Assertions.assertEquals(TaskStatus.CLOSED.toString(), result.getStatus());
    }

    @Test
    @DisplayName("ChangeStatus Task empty DTO Throws Exception")
    public void changeStatusTask_EmptyDTO_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.changeStatus(null));
    }

    @Test
    @DisplayName("ChangeStatus Task no such Task Throws Exception")
    public void changeStatusTask_NoSuchTask_ThrowException() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        Long id = model.getId();

        when(taskDAO.changeStatus(any())).thenReturn(model);
        when(taskDAO.getById(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.changeStatus(id));
    }
    /****************************************************************************
     * getByProjectCode tests
     */
    @Test
    @DisplayName("GetByPrjCode Task when all conditions ok")
    public void getByPrjCodeTask_AllGoodConditions() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        ProjectModel projectModel = model.getProject();
        String code = projectModel.getCode();
        List<TaskModel> list = new ArrayList<>();
        list.add(model);

        when(taskDAO.getByProject(any())).thenReturn(list);
        when(projectDAO.getByCode(any())).thenReturn(projectModel);

        Assertions.assertFalse(taskService.getByPrjCode(code).isEmpty());
    }

    @Test
    @DisplayName("GetByPrjCode Task empty code Throws Exception")
    public void getByPrjCodeTask_EmptyCode_ThrowException() {
        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.getByPrjCode(null));
    }

    @Test
    @DisplayName("GetByPrjCode Task no such Project Throws Exception")
    public void getByPrjCodeTask_NoSuchProject_ThrowException() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        ProjectModel projectModel = model.getProject();
        String code = projectModel.getCode();
        List<TaskModel> list = new ArrayList<>();
        list.add(model);

        when(taskDAO.getByProject(any())).thenReturn(list);
        when(projectDAO.getByCode(any())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.getByPrjCode(code));
    }

    @Test
    @DisplayName("GetByPrjCode Task nothing found Throws Exception")
    public void getByPrjCodeTask_NoResult_ThrowException() {
        TaskModel model = createTaskModel();
        model = setAttributes(model);
        ProjectModel projectModel = model.getProject();
        String code = projectModel.getCode();
        List<TaskModel> list = new ArrayList<>();
        list.add(model);

        when(projectDAO.getByCode(any())).thenReturn(projectModel);

        Assertions.assertThrows(ResponseStatusException.class, ()->taskService.getByPrjCode(code));
    }


    /****************************************************************************
     * Initialisation
     */

    private TaskModel createTaskModel() {
        Long id = 100L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String email = "employee@employee.com";
        EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(id);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setEMail(email);
        employeeModel.setStatus(employeeStatus);

        ProjectModel projectModel = new ProjectModel();
        String code = "001";
        String name = "Project001";
        ProjectStatus projectStatus = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(name);
        projectModel.setStatus(projectStatus);

        Long taskId = 1L;
        String taskName = "task01";
        String taskDescription = "some_description";
        LocalDate executionDate = LocalDate.now().plusDays(2);
        int labourCost = 8;
        TaskModel taskModel = new TaskModel();
        taskModel.setId(taskId);
        taskModel.setProject(projectModel);
        taskModel.setName(taskName);
        taskModel.setDetails(taskDescription);
        taskModel.setEmployee(employeeModel);
        taskModel.setExecutiondate(executionDate);
        taskModel.setLaborCost(labourCost);
        return taskModel;
    }

    private EmployeeModel createAuthor() {
        Long aId = 101L;
        final String afirstName = "AuthorFirstName";
        final String alastName = "AuthorLastName";
        final String account = "Author";
        EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        EmployeeModel authorModel = new EmployeeModel();
        authorModel.setId(aId);
        authorModel.setFirstName(afirstName);
        authorModel.setLastName(alastName);
        authorModel.setAccount(account);
        authorModel.setStatus(employeeStatus);
        return  authorModel;
    }

    private TaskModel setAttributes(TaskModel model) {
        LocalDate now = LocalDate.now();
        LocalDate executionDate = now.plusDays(2);
        TaskStatus taskStatus = TaskStatus.NEW;
        EmployeeModel author = createAuthor();
        model.setCreationdate(now);
        model.setChangedate(now);
        model.setExecutiondate(executionDate);
        model.setStatus(taskStatus);
        model.setAuthor(author);
        return model;
    }
}
