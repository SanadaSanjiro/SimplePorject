package com.digdes.simple.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Класс Сотрудник

@Getter
@Setter
@Entity
@Table(name="employees")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
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
        return "\nEmployee{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surName='" + surName + '\'' +
                ", position='" + position + '\'' +
                ", account='" + account + '\'' +
                ", eMail='" + eMail + '\'' +
                ", status=" + status +
                "}";
    }
}