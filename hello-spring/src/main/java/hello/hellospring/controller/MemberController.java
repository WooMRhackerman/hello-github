package hello.hellospring.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private  MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberserivce) {
		this.memberService = memberserivce;
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findAllMembers();
		model.addAttribute("members",members);
		
		return "/members/memberList";
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		return "/members/craeteMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		return "redirect:/";
	}
}
