package com.digdes.simple.test.dao.employee;

import com.digdes.simple.employee.*;
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

//Инициализация тест контейнера с помощью класса-инициализатора

@SpringBootTest
@ContextConfiguration(initializers = {EmployeeRepositoryTestcontainer.Initializer.class})
@Testcontainers
public class EmployeeRepositoryTestcontainer {

    @Autowired
    EmployeeRepository repository;

    EmployeeModel employeeModel;
    Long id;

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
        final EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        employeeModel = new EmployeeModel();
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setStatus(employeeStatus);
    }

    @AfterEach
    void afterTests() {
        repository.delete(employeeModel);
    }

    @Test
    void memberRepoMethodsTest() {
        //Проверяем что сохранение возвращает непустой результат
        Assertions.assertNotNull(employeeModel = repository.save(employeeModel));

        //Проверяем что getById возвращает модель с правильным id
        id = employeeModel.getId();
        Optional<EmployeeModel> optional = repository.findById(id);
        Assertions.assertEquals(id, optional.get().getId());

        //Проверяем корректность сохранения измененной модели
        String newFirstName = "NewFirstName";
        employeeModel.setFirstName(newFirstName);
        employeeModel=repository.save(employeeModel);
        Assertions.assertEquals(newFirstName, employeeModel.getFirstName());

        //Проверяем что поиск по ранее сохраненному критерию возвращает непустой результат
        EmployeeSrchDTO dto = new EmployeeSrchDTO();
        dto.setFirstname(newFirstName);
        Assertions.assertNotNull(repository.findAll(EmployeeSpecification.getFilters(dto)));

        //Проверяем, что объект отсутсвует в БД после использования метода deleteById
        repository.deleteById(id);
        Assertions.assertFalse(repository.findById(id).isPresent());
    }
}