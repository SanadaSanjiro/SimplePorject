package com.digdes.simple.dao.task;

import com.digdes.simple.dao.employee.EmployeeDAO;
import com.digdes.simple.dao.project.ProjectDAO;
import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.project.ProjectModel;
import com.digdes.simple.model.task.TaskModel;
import com.digdes.simple.model.task.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class TaskDAOTest {
    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    TaskDAO taskDAO;



    @Test
    public void getByIdTask_AllGoodConditions() {
        ProjectModel project = projectDAO.getByCode("p0000001");
        EmployeeModel employee = employeeDAO.getById(5L);
        String details = "Сделать хоть что-нибудь";
        String name = "Задача дня";
        int labourCost = 15;
        LocalDate now = LocalDate.now(); // текущая дата
        LocalDate planDate = now.plusDays(labourCost/8);
        TaskModel model = new TaskModel();
        model.setName(name);
        model.setProject(project);
        model.setDetails(details);
        model.setEmployee(employee);
        model.setAuthor(employee);
        model.setLaborCost(labourCost);
        model.setExecutiondate(planDate);
        model.setStatus(TaskStatus.NEW);
        model.setChangedate(now);
        model.setCreationdate(now);
        model.setStatus(TaskStatus.NEW);
        model = taskDAO.create(model);
        System.out.println(model);
    }
}
