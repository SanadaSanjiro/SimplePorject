package com.digdes.simple.service.member;

import com.digdes.simple.dto.member.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO create(MemberDTO dto);

    MemberDTO delete(String code, Long id);

    List<MemberDTO> getByPrjCode(String code);

    MemberDTO getById(String code, Long id);
}
