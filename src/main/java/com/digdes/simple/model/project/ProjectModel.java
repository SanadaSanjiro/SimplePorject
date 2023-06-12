package com.digdes.simple.model.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name="projects")
public class ProjectModel implements Serializable {
    @Id
    private String code;
    private String uuid;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
}
