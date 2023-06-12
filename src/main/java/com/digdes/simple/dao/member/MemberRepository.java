package com.digdes.simple.dao.member;

import com.digdes.simple.model.member.MemberModel;
import com.digdes.simple.model.member.MembersKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberModel, MembersKey> {
    Optional<List<MemberModel>> getAllById(MembersKey key);
}
