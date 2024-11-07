package com.practice.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.member.dto.MemberDTO;
import com.practice.member.entity.MemberEntity;
import com.practice.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
		memberRepository.save(memberEntity);
	}

	public MemberDTO login(MemberDTO memberDTO) {
		Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
		if (byMemberEmail.isPresent()) {
			MemberEntity memberEntity = byMemberEmail.get();
			if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				return MemberDTO.toMemberDTO(memberEntity);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public List<MemberDTO> findAll() {
		List<MemberEntity> memberEntityList = memberRepository.findAll();
		List<MemberDTO> memberDTOList = new ArrayList<>();
		for (MemberEntity memberEntity : memberEntityList) {
			memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));

		}
		return memberDTOList;
	}
}
