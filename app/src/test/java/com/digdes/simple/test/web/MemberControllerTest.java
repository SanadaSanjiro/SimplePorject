package com.digdes.simple.test.web;

import com.digdes.simple.MemberController;
import com.digdes.simple.member.MemberDTO;
import com.digdes.simple.member.MemberService;
import com.digdes.simple.member.Role;
import com.digdes.simple.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberControllerTest extends BaseTest {
    @Mock
    MemberService service;

    @InjectMocks
    MemberController controller;

    @Test
    @DisplayName("Get Member by project code and employee id with a controller AllGoodConditions")
    public void getByIdMember_AllGoodConditions() {
        Long id = 1L;
        String code = "001";
        String role = Role.MANAGER.toString();
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        dto.setRole(role);
        when(service.getById(any(), any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.getById(code, id));
        verify(service, Mockito.times(1)).getById(Mockito.any(), Mockito.any());
    }

    @Test
    @DisplayName("Create Member with a controller AllGoodConditions")
    public void createMember_AllGoodConditions() {
        Long id = 1L;
        String code = "001";
        String role = Role.MANAGER.toString();
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        dto.setRole(role);
        when(service.create(any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.create(dto));
        verify(service, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    @DisplayName("Delete Member with a controller AllGoodConditions")
    public void deleteMember_AllGoodConditions() {
        Long id = 1L;
        String code = "001";
        String role = Role.MANAGER.toString();
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        dto.setRole(role);
        when(service.delete(any(), any())).thenReturn(dto);
        Assertions.assertEquals(dto, controller.delete(code, id));
        verify(service, Mockito.times(1)).delete(Mockito.any(), Mockito.any());
    }

    @Test
    @DisplayName("Get Members by project codewith a controller AllGoodConditions")
    public void getByPrjCode_AllGoodConditions() {
        Long id = 1L;
        String code = "001";
        String role = Role.MANAGER.toString();
        MemberDTO dto = new MemberDTO();
        dto.setEmpid(id);
        dto.setPrjcode(code);
        dto.setRole(role);
        List<MemberDTO> list = new ArrayList<>();
        list.add(dto);
        when(service.getByPrjCode(any())).thenReturn(list);
        Assertions.assertFalse(controller.getByPrjCode(code).isEmpty());
        verify(service, Mockito.times(1)).getByPrjCode(Mockito.any());
    }
}
