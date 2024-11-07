package com.practice.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.member.dto.MemberDTO;
import com.practice.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
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
		return "login";
	}

	@GetMapping("/member/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO loginResult = memberService.login(memberDTO);
		if (loginResult != null) {
			session.setAttribute("loginEmail", loginResult.getMemberEmail());
			return "main";
		} else {
			return "login";
		}
	}

	@GetMapping("/member/")
	public String findAll(Model model) {
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "list";
	}
}
