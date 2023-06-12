package com.digdes.simple.employee;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//Класс Сотрудник

@Getter
@Setter
@Entity
@Table(name="employees")
public class EmployeeModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String uid; //не используется, заложено для следующих версий программы
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="surname")
    private String surName;
    private String position;
    private String account;
    @Column(name="email")
    private String eMail;
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surName='" + surName + '\'' +
                ", position='" + position + '\'' +
                ", account='" + account + '\'' +
                ", eMail='" + eMail + '\'' +
                ", status=" + status +
                "}\n";
    }
}