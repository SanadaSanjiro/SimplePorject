package com.digdes.simple.model.member;

import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.project.ProjectModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="members")
public class MemberModel implements Serializable {
    @EmbeddedId
    private MembersKey id;

    @ManyToOne
    @MapsId("empid")
    @JoinColumn(name = "empid")
    private EmployeeModel employee;

    @ManyToOne
    @MapsId("prjcode")
    @JoinColumn(name = "prjcode")
    private ProjectModel project;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return "MemberModel{"+"\nemployee=" + employee +
                ",\nproject=" + project +
                ",\nrole=" + role +
                '}';
    }
}
