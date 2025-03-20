package com.springStudy1.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller // 서블릿 클래스와 같은 역할 - 컨트롤 역할(주소받아서 페이지넘겨줌)
public class MainControl {
	
	@GetMapping("/test") //  localhost/test와 같은 주소로 요청이 들어온다면 동작하겠다(메서드 실행)
	public String testPage() { // 메서드이름은 아무거나
		System.out.println("와 진짜 실행 된다 .... 한글 좋아.....");
		return "hello.html"; // 보여줄 페이지 경로를 리턴시킴
	}
	
	@GetMapping("/signIn")
	public String viewPage() {
		System.out.println("뷰페이지 성공");
		return "signIn.html";
	}
	
	@GetMapping("/") // localhost만 입력해도 index.html이 뜨게
	public String homePage() {
		return "index.html";
	}
	
	@GetMapping("/list") // 유치원클릭했을때 파라미터 "유"를 가져옴.
	public ModelAndView listPage(@RequestParam String type) {
		System.out.println(type);
		ModelAndView mav = new ModelAndView("list.html");// 페이지제공과 데이터도 같이 제공
		//mav.setViewName(type);
		return mav;
	}
}

// 주소 : http://localhost/signIn
// 뷰 페이지 : signIn.html
//		내용 : 아이디, 비밀번호 입력 가능하게

/*
 	컨트롤 클래스의 역할 지정 - @Controller를 클래스 위에 넣기
 	
 	클라이언트의 요청 처리
 		GET방식으로 주소 요청 들어온다면 @GetMapping
 		POST방식으로 주소 요청 들어온다면 @PostMapping
 		
 		@GetMapping("주소")
 			@GetMapping("/list") -> localhost/list 주소 요청 시 동작
 			
 		주소 요청시 실행할 코드는 메서드로 표현한다.
 		@PostMapping("/save")
 		public String boardSave(){
 		
 		}
 		-> localhost/save 주소가 post방식으로 요청되는 경우 boardSave메서드 실행
 */