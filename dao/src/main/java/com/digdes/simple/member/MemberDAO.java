package com.digdes.simple.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.digdes.simple.project.ProjectModel;

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
                return model.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MemberModel deleteById (MembersKey key) {
        try {
            MemberModel memberModel = memberRepository.findById(key).get();
            memberRepository.deleteById(key);
            return memberModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MemberModel> getByProject(ProjectModel projectModel) {
        try {
            Optional<List<MemberModel>> optional = memberRepository.getByProject(projectModel);
            if (optional.isPresent()) {
                List<MemberModel> members =optional.get();
                return members;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
