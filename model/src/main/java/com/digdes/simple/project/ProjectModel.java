package com.digdes.simple.project;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String uuid; //не используется, заложено для следующих версий программы
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Override
    public String toString() {
        return "ProjectModel{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                "}\n";
    }
}
