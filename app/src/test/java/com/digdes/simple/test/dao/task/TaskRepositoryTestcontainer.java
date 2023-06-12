package com.digdes.simple.test.dao.task;

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeRepository;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.project.*;
import com.digdes.simple.task.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

@SpringBootTest
@ContextConfiguration(initializers = {TaskRepositoryTestcontainer.Initializer.class})
@Testcontainers
public class TaskRepositoryTestcontainer {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    EmployeeModel employeeModel;
    EmployeeModel authorModel;
    ProjectModel projectModel;
    TaskModel taskModel;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("taskmanagerdb")
            .withUsername("admin")
            .withPassword("MyP@ss4DB");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url="
                            + String.format("jdbc:postgresql://localhost:%d/taskmanagerdb?loggerLevel=OFF",
                            postgreSQLContainer.getFirstMappedPort()),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.liquibase.enabled=true"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Value("${spring.datasource.url}")
    private String url;

    @Test
    void checkDatasourceUrl() {
        Assertions.assertNotEquals(
                "jdbc:postgresql://localhost:5432/taskmanagerdb", url);
    }

    @BeforeEach
    void testInit() {
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        employeeModel = new EmployeeModel();
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setStatus(employeeStatus);
        employeeModel = employeeRepository.save(employeeModel);

        final String afirstName = "AuthorName";
        final String alastName = "AuthorLastName";
        authorModel = new EmployeeModel();
        authorModel.setFirstName(afirstName);
        authorModel.setLastName(alastName);
        authorModel.setStatus(employeeStatus);
        authorModel = employeeRepository.save(authorModel);

        projectModel = new ProjectModel();
        String code = "001";
        String name = "Project001";
        ProjectStatus projectStatus = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(name);
        projectModel.setStatus(projectStatus);
        projectRepository.save(projectModel);

        String taskName = "task01";
        String taskDescription = "some_description";
        TaskStatus taskStatus = TaskStatus.NEW;
        taskModel = new TaskModel();
        taskModel.setName(taskName);
        taskModel.setStatus(taskStatus);
        taskModel.setDetails(taskDescription);
        taskModel.setEmployee(employeeModel);
        taskModel.setAuthor(authorModel);
    }

    @AfterEach
    void afterTests() {
        taskRepository.delete(taskModel);
        projectRepository.delete(projectModel);
        employeeRepository.delete(employeeModel);
        employeeRepository.delete(authorModel);
    }

    @Test
    void taskRepoMethodsTest() {
        //Проверяем что сохранение возвращает непустой результат
        Assertions.assertNotNull(taskModel = taskRepository.save(taskModel));
        Long id = taskModel.getId();

        //Проверяем что getById возвращает модель с правильным id
        Optional<TaskModel> optional = taskRepository.findById(id);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(id, optional.get().getId());

        //Проверяем корректность сохранения измененной модели
        String newName = "task02";
        taskModel.setName(newName);
        taskModel=taskRepository.save(taskModel);
        Assertions.assertEquals(newName, taskModel.getName());

        //Проверяем что поиск по ранее сохраненному критерию возвращает непустой результат
        TaskSrchDTO dto = new TaskSrchDTO();
        dto.setName(newName);
        Assertions.assertNotNull(taskRepository.findAll(TaskSpecification.getFilters(dto)));

        //Проверяем поиск задач по проекту
        ProjectModel project = taskModel.getProject();
        Assertions.assertNotNull(taskRepository.getByProject(project));
    }
}
