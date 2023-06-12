package com.digdes.simple.model.task;

import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.project.ProjectModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class TaskModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "prjcode")
    private ProjectModel project; // Проект, в рамках которого создана задача
    private String name;
    private String details; //Описание задачи
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee; //Исполнитель
    @Column(name="labor_cost")
    private int laborCost; //Трудозатраты
    @Column(name="execution_date")
    private LocalDate executiondate; //Крайний срок
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private EmployeeModel author;
    @Column(name="creation_date")
    private LocalDate creationdate;
    @Column(name="change_date")
    private LocalDate changedate;

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", \nproject=" + project +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", \nemployee=" + employee +
                ", laborCost=" + laborCost +
                ", executionDate=" + executiondate +
                ", status=" + status +
                ", \nauthor=" + author +
                ", creationDate=" + creationdate +
                ", changeDate=" + changedate +
                '}';
    }
}
