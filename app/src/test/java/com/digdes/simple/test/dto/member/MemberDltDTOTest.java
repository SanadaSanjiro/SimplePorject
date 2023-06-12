package com.digdes.simple.test.dto.member;

import com.digdes.simple.member.MemberDTO;
import com.digdes.simple.member.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberDltDTOTest {
    @Test
    @DisplayName("MemberDTO setters and getters are ok")
    public void memberDTO_CheckSettersGetters() {
        final String prjcode = "p001";
        final Role role = Role.DEVELOPER;
        MemberDTO dto = new MemberDTO();
        dto.setPrjcode(prjcode);
        dto.setRole(role.toString());
        Assertions.assertEquals(prjcode, dto.getPrjcode());
        Assertions.assertEquals(role.toString(), dto.getRole());
    }
}
