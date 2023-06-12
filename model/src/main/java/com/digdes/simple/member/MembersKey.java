package com.digdes.simple.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class MembersKey implements Serializable {

    @Column(name="prjcode")
    private String prjcode;
    @Column(name="empid")
    private Long empid;

    @Override
    public int hashCode() {
        final int prime = 31;
        return (prjcode.hashCode()*prime + empid.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj==null || obj.getClass()!=this.getClass()) {return false;}
        MembersKey gk = (MembersKey) obj;
        if (!(gk.getEmpid()).equals(empid)) {return false;}
        if (!gk.getPrjcode().equals(prjcode)) {return false;}
        return true;
    }
}
