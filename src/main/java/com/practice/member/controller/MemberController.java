package com.practice.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.member.dto.MemberDTO;
import com.practice.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/member/save")
	public String saveForm() {
		return "save";
	}

	@PostMapping("/member/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		System.out.println("memberDTO = " + memberDTO);
		memberService.save(memberDTO);
		return null;
	}
}
