package com.example.loadtest.domain.member.repository;

import com.example.loadtest.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
