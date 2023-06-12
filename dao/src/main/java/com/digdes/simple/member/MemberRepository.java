package com.digdes.simple.member;

import org.springframework.data.jpa.repository.JpaRepository;
import com.digdes.simple.project.ProjectModel;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberModel, MembersKey> {
    Optional<List<MemberModel>> getByProject(ProjectModel project);
}
