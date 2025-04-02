package com.talk.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.talk.Dto.MemberSignInDto;
import com.talk.Dto.MemberSignUpDto;
import com.talk.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService; 
	
	// 회원가입 요청
	@PostMapping("/signUp")
	public String signUp(MemberSignUpDto memberSignUpDto, Model model) {
		return null;
	}
	 // 로그인 요청
	@PostMapping("/signIn")
	public String signIn(MemberSignInDto memberSignInDto, Model model) {
		return null;
	}
}
