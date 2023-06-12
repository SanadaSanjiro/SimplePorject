package com.digdes.simple.dao.employee;

import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.employee.EmployeeStatus;
import org.junit.jupiter.api.Assertions;
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

import java.util.List;
import java.util.Optional;

//Инициализация тест контейнера с помощью класса-инициализатора

@SpringBootTest
@ContextConfiguration(initializers = {EmployeeRepositoryTestcontainer.Initializer.class})
@Testcontainers
public class EmployeeRepositoryTestcontainer {

    @Autowired
    EmployeeRepository repository;

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


    @Test
    void employeeRepoFindById() {
        Long id = 1L;
        Optional<EmployeeModel> optional = repository.findById(id);
        Assertions.assertNotNull(optional.get());
    }

    @Test
    void employeeRepoCreate() {
        final String firstName = "FirstName";
        final String lastName = "LastName";
        EmployeeModel dbModel = new EmployeeModel();
        dbModel.setFirstName(firstName);
        dbModel.setLastName(lastName);
        dbModel.setStatus(EmployeeStatus.ACTIVE);
        dbModel=repository.save(dbModel);
        Assertions.assertNotNull(dbModel.getId());
    }

    @Test
    void employeeRepoGetAll() {
        List<EmployeeModel> ems = repository.findAll();
        Assertions.assertFalse(ems.isEmpty());
    }
}