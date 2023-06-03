package com.digdes.simple;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        /**
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(100L);
        dto.setUid("UID100L");
        dto.setFirstname("Василий");
        dto.setLastname("Пупкин");
        dto.setSurname("Васильевич");
        dto.setPosition("босс");
        dto.setAccount("vasyanya");
        dto.setEmail("vasya@mail.ru");
        dto.setStatus("DELETED");
        EmployeeModel model = EmployeeMapper.map(dto);
        System.out.println(model);

        dto = EmployeeMapper.map(model);
        System.out.println(dto);

        System.out.println("Получаем сотрудника с id=1");
        System.out.println(employeeDAO.getById(1L));
        System.out.println("Получаем список всех сотрудников");
        System.out.println(employeeDAO.getAll());
        System.out.println("Создаем нового сотрудника");
        EmployeeModel em = new EmployeeModel();
        em.setFirstName("Федор");
        em.setLastName("Пупкин");
        em.setEMail("fedor@pupkin.biz");
        em.setPosition("Бухгалтер");
        em.setEMail("dvorink@dvornik.com");
        em.setAccount("fedunya");
        em.setStatus(EmployeeStatus.ACTIVE);
        em.setSurName("Федорович");
        em.setUid("U00001");
        em=employeeDAO.create(em);
        System.out.println(em);
        em.setEMail("buhgalter@buhgalter.com");
        System.out.println("Изменяем e-mail сотрудника");
        System.out.println(employeeDAO.update(em));
        System.out.println("Удаляем сотрудника");
        System.out.println(employeeDAO.deleteById(em.getId()));
        System.out.println("Получаем список всех сотрудников");
        System.out.println(employeeDAO.getAll());
        System.out.println("Применяем фильтр");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1l);
        System.out.println("id = 1");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstname("Иван");
        System.out.println("firstname = Иван");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setLastname("Петров");
        System.out.println("lastname = Петров");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setSurname("Иванович");
        System.out.println("surname = Иванович");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setPosition("разработчик");
        System.out.println("position = разработчик");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setAccount("ivanov");
        System.out.println("account = ivanov");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail("petrovich@digdes.com");
        System.out.println("email = petrovich@digdes.com");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setStatus(EmployeeStatus.ACTIVE.name());
        System.out.println("статус = ACTIVE");
        System.out.println(employeeDAO.getFiltered(employeeDTO));
        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(10000L);
        System.out.println("несуществующий id");
        System.out.println(employeeDAO.getFiltered(employeeDTO));


        /**
        simpleRepository.findAll().forEach(System.out::println);
        employeeRepository.findAll().forEach(System.out::println);
        System.out.println(employeeRepository.findById(1L));
        **/
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
