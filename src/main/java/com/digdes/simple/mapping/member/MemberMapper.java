package com.digdes.simple.mapping.member;


// Преобразование модели в DTO и обратно с полным набором полей.

import com.digdes.simple.dto.member.MemberDTO;
import com.digdes.simple.model.member.MemberModel;
import com.digdes.simple.model.member.MembersKey;
import com.digdes.simple.model.member.Role;
import org.springframework.util.ObjectUtils;

public class MemberMapper {
    public static MemberModel map (MemberDTO dto) {
        MemberModel model = new MemberModel();
        if (!ObjectUtils.isEmpty(dto.getPrjcode())&&
                !ObjectUtils.isEmpty(dto.getEmpid())) {
            MembersKey mk = new MembersKey();
            mk.setEmpid(dto.getEmpid());
            mk.setPrjcode(dto.getPrjcode());
        }
        if (!ObjectUtils.isEmpty(dto.getRole())) {
            switch (dto.getRole()) {
                case ("MANAGER"):
                    model.setRole(Role.MANAGER);
                    break;
                case ("ANALYST"):
                    model.setRole(Role.ANALYST);
                    ;
                    break;
                case ("DEVELOPER"):
                    model.setRole(Role.DEVELOPER);
                    break;
                case ("TESTER"):
                    model.setRole(Role.TESTER);
            }
        }
        return model;
    }

    public static MemberDTO map (MemberModel model) {
        MemberDTO dto = new MemberDTO();
        if (!ObjectUtils.isEmpty(model.getId())) {
            MembersKey mk = model.getId();
            dto.setPrjcode(mk.getPrjcode());
            dto.setEmpid(mk.getEmpid());
        }
        if (!ObjectUtils.isEmpty(model.getRole())) {
            switch (model.getRole().name()) {
                case ("MANAGER") :
                    dto.setRole("MANAGER");
                    break;
                case ("ANALYST"):
                    dto.setRole("ANALYST");
                    break;
                case ("DEVELOPER") :
                    dto.setRole("DEVELOPER");
                    break;
                case ("TESTER"):
                    dto.setRole("TESTER");
                    break;
            }
        }
        return dto;
    }
}
