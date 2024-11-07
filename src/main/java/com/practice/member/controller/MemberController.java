package com.practice.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@GetMapping("/member/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "detail";
	}

	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String myEmail = (String) session.getAttribute("loginEmail");
		MemberDTO memberDTO = memberService.updateForm(myEmail);
		model.addAttribute("updateMember", memberDTO);
		return "update";
	}

	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/" + memberDTO.getId();
	}

	@GetMapping("member/delete/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		memberService.deleteById(id);
		return "redirect:/member/";
	}

	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@PostMapping("/member/email-check")
	public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
		String checkResult = memberService.emailCheck(memberEmail);
		return checkResult;
	}
}
