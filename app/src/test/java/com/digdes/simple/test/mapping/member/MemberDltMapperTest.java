package com.digdes.simple.test.mapping.member;

import com.digdes.simple.member.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberDltMapperTest {
    @Test
    @DisplayName("MemberDltMapper maps all fields model->DTO")
    void memberMappper_MapDTOToModel() {
        final Long empid = 1L;
        final String prjcode = "p001";
        MemberDltDTO dto = new MemberDltDTO();
        dto.setEmpid(empid);
        dto.setPrjcode(prjcode);

        MemberModel model = MemberDltMapper.map(dto);
        Assertions.assertEquals(empid, model.getId().getEmpid());
        Assertions.assertEquals(prjcode, model.getId().getPrjcode());
    }
}
