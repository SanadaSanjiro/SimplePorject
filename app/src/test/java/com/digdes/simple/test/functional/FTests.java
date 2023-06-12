package com.digdes.simple.test.functional;
import com.digdes.simple.EmployeeController;
import com.digdes.simple.employee.EmployeeCrtDTO;
import com.digdes.simple.employee.EmployeeStatus;
import com.digdes.simple.employee.EmployeeUpdDTO;
import com.digdes.simple.employee.EmployeeViewDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ContextConfiguration(initializers = {FTests.Initializer.class})
@Testcontainers
public class FTests {
    String firstname = "FirstName";
    String lastname = "LastName";
    String surname = "surname";
    String position = "position";
    String account = "account";
    String email = "email";
    String password = "password";

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
    @Autowired
    EmployeeController employeeController;

    @Test
    @DisplayName("Functional test 1. New Employee creation")
    public void newEmployeeCreationTest() {

        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        Long id;
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        dto.setSurname(surname);
        dto.setPosition(position);
        dto.setAccount(account);
        dto.setEmail(email);
        dto.setPassword(password);

        EmployeeViewDTO viewDTO = employeeController.create(dto);
        id = viewDTO.getId();
        Assertions.assertNotNull(id);
        Assertions.assertEquals(lastname, viewDTO.getLastname());
        Assertions.assertEquals(firstname, viewDTO.getFirstname());
        Assertions.assertEquals(surname, viewDTO.getSurname());
        Assertions.assertEquals(position, viewDTO.getPosition());
        Assertions.assertEquals(account, viewDTO.getAccount());
        Assertions.assertEquals(email, viewDTO.getEmail());
        Assertions.assertEquals(EmployeeStatus.ACTIVE.toString(), viewDTO.getStatus());
    }

    @Test
    @DisplayName("Functional test 2. Employee update")
    public void employeeUpdateTest() {
        Long id;
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        EmployeeViewDTO viewDTO = employeeController.create(dto);
        id = viewDTO.getId();

        EmployeeUpdDTO updDto = new EmployeeUpdDTO();
        updDto.setId(id);
        updDto.setFirstname(firstname);
        updDto.setLastname(lastname);
        updDto.setSurname(surname);
        updDto.setPosition(position);
        updDto.setEmail(email);

        viewDTO = employeeController.update(updDto);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(lastname, viewDTO.getLastname());
        Assertions.assertEquals(firstname, viewDTO.getFirstname());
        Assertions.assertEquals(surname, viewDTO.getSurname());
        Assertions.assertEquals(position, viewDTO.getPosition());
        Assertions.assertEquals(email, viewDTO.getEmail());
        Assertions.assertEquals(EmployeeStatus.ACTIVE.toString(), viewDTO.getStatus());
    }

    @Test
    @DisplayName("Functional test 3. Employee delete")
    public void employeeDeleteTest() {
        Long id;
        EmployeeCrtDTO dto = new EmployeeCrtDTO();
        dto.setFirstname(firstname);
        dto.setLastname(lastname);
        EmployeeViewDTO viewDTO = employeeController.create(dto);
        id = viewDTO.getId();

        viewDTO = employeeController.delete(id);
        Assertions.assertEquals(EmployeeStatus.DELETED.toString(), viewDTO.getStatus());
    }

}