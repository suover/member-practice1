package com.practice.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
