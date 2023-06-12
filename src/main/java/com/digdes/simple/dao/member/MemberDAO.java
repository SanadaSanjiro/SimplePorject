package com.digdes.simple.dao.member;

import com.digdes.simple.model.member.MemberModel;
import com.digdes.simple.model.member.MembersKey;
import com.digdes.simple.model.project.ProjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    @Autowired
    private MemberRepository memberRepository;

    public MemberModel create(MemberModel model) {
        try {
            return memberRepository.save(model);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public MemberModel getById(MembersKey key) {
        try {
            Optional<MemberModel> model = memberRepository.findById(key);
            if (model.isPresent()) {
                return memberRepository.findById(key).get();
            } else {
                return null;
            }
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

    public List<MemberModel> getByProject(ProjectModel projectModel) {
        try {
            List<MemberModel> members = memberRepository.getByProject(projectModel).get();
            return members;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
