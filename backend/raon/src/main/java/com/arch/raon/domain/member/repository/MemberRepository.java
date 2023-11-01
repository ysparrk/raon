package com.arch.raon.domain.member.repository;

import java.util.Optional;

import com.arch.raon.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByNickname(String nickname);

}
