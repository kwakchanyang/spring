package com.bookSystem.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookSystem.DTO.MemberDto;

@Controller
public class MainController {
	
	@GetMapping("/test") //얘가 주소지 return이 주소가 아님
	public String main(Model model) {  // ModelAndView는 객체new생성해서 사용 but Model은 ctrl+space누르면 변수이름 자동추천해줌(변수이름은 알아보기 쉽게!)> Model은 변수만들면 객체생성없이 바로 사용가능
			
		model.addAttribute("name", "곽찬양"); // ModelAndView는 return해야 했지만 Model은 model리턴안하고 thymeleaf를 통해 자동으로 표현됨.
		model.addAttribute("age", 30);
		model.addAttribute("tel","010-2343-9874");
		model.addAttribute("address","남아프리카 공화국");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setAge(29);
		memberDto.setId("rim");
		memberDto.setName("김은나");
		memberDto.setTel("010-8888-0000");
		
		model.addAttribute("user", memberDto);
		
		List<String> animal = new ArrayList<>();
		animal.add("개"); animal.add("호랑이");
		animal.add("뱀"); animal.add("닭");
		animal.add("토끼"); animal.add("소");
				  
		model.addAttribute("animal",animal);
		
		return "test"; // index.html안해도 pom.xml에 suffix=.html해놔서 
	}
	

	
}


/*
 	스프링의 동작은 기본적으로 mvc 패턴이다.
 	m : 모델 (데이터 다루기 위한 클래스)
 	v : 뷰 (화면 페이지 - html)
 	c : 컨트롤
 	 
 	요청 -> 컨트롤 -> 로직(서비스) ->DAO(레포지토리) -> 서비스 -> 컨트롤 -> client

 
 */