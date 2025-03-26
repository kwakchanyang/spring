package com.bookSystem.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookSystem.DTO.MemberDto;
import com.bookSystem.Service.BookService;
import com.bookSystem.Service.MemberService;

@Controller
public class MainController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		MemberDto memberDto = new MemberDto();
		model.addAttribute("memberDto",memberDto);
		
		return "index";
	}
	
	@PostMapping("/signIn") // ┌객체를 아예 위에서 보내놔서 아이디,비번입력하고 로그인 버튼누르면 객체에 알아서 저장이되서 그냥 가져오면됨
	public String login(MemberDto memberDto ,Model model) { // 원래는 로그인하면 주소가 localhost/signin으로 바뀌지만 
		System.out.println(memberDto.getEmail());
		
		// Model model이게 컨트롤러에서 나온 데이터를 담고서 뷰(index.html)로 보내줌 
		// 로그인처리를 진행하려면 service의 메서드를 호출한다.
		// member와 관련된 것은 MemberService에서 처리한다.
		// 컨트롤 쪽에서는 로그인처리과정이 어떻게 진행되고 하는지 전혀 몰라도 된다.
		// 그냥 service쪽 메서드를 호출하면 된다.
		boolean isSuccess = memberService.signIn(memberDto);
		
		if(isSuccess) {
			
			return "redirect:/"; // 이 주소로 변경해서 재요청해라 > 위의 GetMapping("/")동작함 > 로그인눌러도 localhost뜸
		}
		// 로그인 실패시 index.html 다시 돌아가기
		model.addAttribute("fail",1); // fail안에 null아니면 1이 들어감. fail은 model이라는 객체안에 들어있은 데이터임
		return "index";
	}
	
	
	
//	@GetMapping("/test") //얘가 주소지 return이 주소가 아님
//	public String main(Model model) {  // ModelAndView는 객체new생성해서 사용 but Model은 ctrl+space누르면 변수이름 자동추천해줌(변수이름은 알아보기 쉽게!)> Model은 변수만들면 객체생성없이 바로 사용가능
//			
//		model.addAttribute("name", "곽찬양"); // ModelAndView는 return해야 했지만 Model은 model리턴안하고 thymeleaf를 통해 자동으로 표현됨.
//		model.addAttribute("age", 30);
//		model.addAttribute("tel","010-2343-9874");
//		model.addAttribute("address","남아프리카 공화국");
//		
//		MemberDto memberDto = new MemberDto();
//		memberDto.setAge(29);
//		memberDto.setId("rim");
//		memberDto.setName("김은나");
//		memberDto.setTel("010-8888-0000");
//		
//		model.addAttribute("user", memberDto);
//		
//		List<String> animal = new ArrayList<>();
//		animal.add("개"); animal.add("호랑이");
//		animal.add("뱀"); animal.add("닭");
//		animal.add("토끼"); animal.add("소");
//				  
//		model.addAttribute("animal",animal);
//		
//		return "test"; // index.html안해도 pom.xml에 suffix=.html해놔서 
//	}
//	
//	
//	@GetMapping("/test2")
//	public String test() {
//		return "test2";
//	}

	
}


/*
 	스프링의 동작은 기본적으로 mvc 패턴이다.
 	m : 모델 (데이터 다루기 위한 클래스)
 	v : 뷰 (화면 페이지 - html)
 	c : 컨트롤
 	 
 	요청 -> 컨트롤 -> 로직(서비스) ->DAO(레포지토리) -> 서비스 -> 컨트롤 -> client

 
 */