package com.talk.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.talk.Dto.MemberSignInDto;
import com.talk.Dto.MemberSignUpDto;
import com.talk.service.BoardService;

@Controller
public class MainController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/") // 메인페이지의 요구사항 보기( 로그인창 띄운다, 최신글 보인다  등등)
	public String home(Model model) { 
		
		//최근글 5개 가져오기
		model.addAttribute("recentList", boardService.boardRecent());
		// 인기글 5개 가져오기
		model.addAttribute("popularList", boardService.boardPopular());
		
		// 로그인/회원가입 폼에서 데이터를 바인딩하기 위해 새로운 객체 생성하여 model에 추가
		// 폼(form)과 DTO객체를 연결하려면 뷰에서 사용할 수 있는 비어있는 객체를 먼저 모델에 추가해야 함
		// 예를 들어, 로그인 폼에서 MemberSignInDto를 사용한다면, 컨트롤러에서 초기 빈 객체를 넘겨줘야함 그래야 th:object or ${memberSignInDto}같은 템플릿 엔진에서 폼을 자동으로 바인딩 가능
		model.addAttribute("memberSignInDto", new MemberSignInDto());
		model.addAttribute("memberSignUpDto", new MemberSignUpDto());
		
		return "index";
	}
	
}
