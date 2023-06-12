package com.digdes.simple.dao.member;

import com.digdes.simple.dto.project.ProjectDTO;
import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.member.MemberModel;
import com.digdes.simple.model.member.MembersKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    @Autowired
    private MemberRepository memberRepository;

    public MemberModel getById(MembersKey key) {
        try {
            return memberRepository.findById(key).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MemberModel deleteById (MembersKey key) {
        MemberModel memberModel = getById(key);
        try {
            memberRepository.deleteById(key);
            return memberModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MemberModel> getAllByCode(String code) {
        try {
            return memberRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
