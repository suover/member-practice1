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

	public MemberDTO findById(Long id) {
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
		if (optionalMemberEntity.isPresent()) {
			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		} else {
			return null;
		}
	}

	public MemberDTO updateForm(String myEmail) {
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
		if (optionalMemberEntity.isPresent()) {
			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		} else {
			return null;
		}
	}

	public void update(MemberDTO memberDTO) {
		memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
	}

	public void deleteById(Long id) {
		memberRepository.deleteById(id);
	}

	public String emailCheck(String memberEmail) {
		Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
		if (byMemberEmail.isPresent()) {
			return null;
		} else {
			return "ok";
		}
	}
}
