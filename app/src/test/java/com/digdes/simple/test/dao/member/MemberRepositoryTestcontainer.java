package com.digdes.simple.test.dao.member;

//Инициализация тест контейнера с помощью класса-инициализатора

import com.digdes.simple.employee.EmployeeModel;
import com.digdes.simple.employee.EmployeeRepository;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.member.*;
import com.digdes.simple.project.ProjectModel;
import com.digdes.simple.project.ProjectRepository;
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
@ContextConfiguration(initializers = {MemberRepositoryTestcontainer.Initializer.class})
@Testcontainers
public class MemberRepositoryTestcontainer {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    EmployeeModel employeeModel;
    ProjectModel projectModel;
    MemberModel memberModel;
    MembersKey mk;

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
        Long id = 1L;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final EmployeeStatus employeeStatus = EmployeeStatus.ACTIVE;
        employeeModel = new EmployeeModel();
        employeeModel.setId(id);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setStatus(employeeStatus);
        employeeModel=employeeRepository.save(employeeModel);

        projectModel = new ProjectModel();
        String code = "001";
        String prjName = "project001";
        projectModel.setCode(code);
        projectModel.setName(prjName);
        projectModel=projectRepository.save(projectModel);

        mk = new MembersKey();
        mk.setEmpid(id);
        mk.setPrjcode(code);

        Role role = Role.TESTER;
        memberModel = new MemberModel();
        memberModel.setId(mk);
        memberModel.setRole(role);
        memberModel.setEmployee(employeeModel);
        memberModel.setProject(projectModel);
    }

    @AfterEach
    void afterTests() {
        memberRepository.delete(memberModel);
        projectRepository.delete(projectModel);
        employeeRepository.delete(employeeModel);
    }

    @Test
    void memberRepoMethodsTest() {
        //Проверяем что сохранение возвращает модель с правильным id
        Assertions.assertEquals(mk, memberRepository.save(memberModel).getId());

        //Проверяем что getById возвращает модель с правильным id
        Optional<MemberModel> optional = memberRepository.findById(mk);
        Assertions.assertEquals(mk, optional.get().getId());

        //Проверяем, что объект отсутсвует в БД после использования метода deleteById
        memberRepository.deleteById(mk);
        Assertions.assertFalse(memberRepository.findById(mk).isPresent());
    }
}
