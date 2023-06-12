package com.digdes.simple.test.dao.project;

import com.digdes.simple.project.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(initializers = {ProjectRepositoryTestcontainer.Initializer.class})
@Testcontainers
public class ProjectRepositoryTestcontainer {

    @Autowired
    ProjectRepository repository;

    ProjectModel projectModel;
    String code;

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

    @BeforeEach
    void testInit() {
        projectModel = new ProjectModel();
        code = "001";
        String prjName = "project001";
        ProjectStatus status = ProjectStatus.DRAFT;
        projectModel.setCode(code);
        projectModel.setName(prjName);
        projectModel.setStatus(status);
    }

    @AfterEach
    void afterTests() {
        repository.delete(projectModel);
    }

    @Test
    void projectRepoMethodsTest() {
        //Проверяем что сохранение возвращает непустой результат
        Assertions.assertNotNull(projectModel = repository.save(projectModel));

        //Проверяем что getById возвращает модель с правильным id
        Optional<ProjectModel> optional = repository.getByCode(code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(code, optional.get().getCode());

        //Проверяем корректность сохранения измененной модели
        String newName = "project002";
        projectModel.setName(newName);
        projectModel=repository.save(projectModel);
        Assertions.assertEquals(newName, projectModel.getName());

        //Проверяем что поиск по ранее сохраненному критерию возвращает непустой результат
        ProjectSrchDTO dto = new ProjectSrchDTO();
        dto.setName(newName);
        Assertions.assertNotNull(repository.findAll(ProjectSpecification.getFilters(dto)));
    }
}
