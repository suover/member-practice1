package com.practice.member.service;

import org.springframework.stereotype.Service;

import com.practice.member.dto.MemberDTO;
import com.practice.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
	}
}
