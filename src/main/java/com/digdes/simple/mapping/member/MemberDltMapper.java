package com.digdes.simple.mapping.member;

import com.digdes.simple.dto.member.MemberDTO;
import com.digdes.simple.model.member.MemberModel;
import com.digdes.simple.model.member.MembersKey;
import org.springframework.util.ObjectUtils;

// Класс для преобразование DTO в модель при удалении сотрудника из команды проекта.
public class MemberDltMapper {
    public static MemberModel map (MemberDTO dto) {
        MemberModel model = new MemberModel();
        if (!ObjectUtils.isEmpty(dto.getPrjcode())&&
                !ObjectUtils.isEmpty(dto.getEmpid())) {
            MembersKey mk = new MembersKey();
            mk.setEmpid(dto.getEmpid());
            mk.setPrjcode(dto.getPrjcode());
            model.setId(mk);
        }
        return model;
    }
}
