package com.digdes.simple.web;

import com.digdes.simple.dto.member.MemberDTO;
import com.digdes.simple.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name= "MemberController", description = "Контроллер команд проекта")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "Добавить сотрудника в проект")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDTO create(@RequestBody MemberDTO dto) {
        return memberService.create(dto);
    }

    @Operation(summary = "Удалить сотрудника из команды проекта")
    @DeleteMapping(value = "/delete/{code}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDTO delete(@PathVariable String code, @PathVariable Long id) {
        return memberService.delete(code, id);
    }

    @Operation(summary = "Получить список сотрудников по коду проекта")
    @GetMapping(value = "/get/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MemberDTO> getByPrjCode(@PathVariable String code) {
        return memberService.getByPrjCode(code);
    }

    @Operation(summary = "Получить данные участника проекта по его id и коду проекта")
    @GetMapping(value = "/get/{code}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDTO getById(@PathVariable String code, @PathVariable Long id) {
        return memberService.getById(code, id);
    }
}
