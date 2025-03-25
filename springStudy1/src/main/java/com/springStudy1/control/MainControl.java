package com.springStudy1.control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springStudy1.DTO.School;
import com.springStudy1.DTO.User;
import com.springStudy1.service.SchoolService;
import com.springStudy1.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller // 서블릿 클래스와 같은 역할 - 컨트롤 역할(주소받아서 페이지넘겨줌)을 한다는 것을 스프링에게 알려주기
			// 컨트롤러 : 클라이언트의 요청(url)을 처리하고, 필요한 데이터를 서비스 계층으로부터 받아와 뷰에 전달하는 역할
public class MainControl {
	
	@Autowired  // 의존성 주입 담당
				// final붙여서 생성자메서드통해 초기화 안해주고 final안붙이고 Autowired 해주면 maincontrol만들때 schoolservice객체도 들어온다.(@service라고 해놔서 자동주입이 된것이다 > Bean으로 직접 등록을 해주거나, @Service해주면 이게 Bean으로 등록이된다.) 
				// 근데 권장하는 방법은 생성자메서드(final)해주는 방법 / int num;num=10 방식을 쓸거냐 int num=10;을 할거냐
	private SchoolService schoolService; // 이렇게 변수선언해서 주입해줄거냐 or 생성자메서드를 사용해서 주입해줄거냐 (차이없음) >> 얘를 써줘서 스프링에게 필요한그것을 내가준것임 (그럼 스프링이 알아서 일해줄것임)
	// 펜뚜껑 닫아주는 기계가 있다 > 기계가 알아야할것은 어떤뚜껑이고 어떤펜인지를 알려줘야함 (Bean의 형태로 등록이 되어야함) > 그래야 @Autowired가 동작할수있음
	// 용도 : @Autowired는 이미 존재하는 빈을 주입받기 위해 사용되며, @Bean은 새로운 빈을 정의하고 생성하기 위해 사용됨. 주입 방식: @Autowired는 Spring 컨테이너에서 관리되는 빈을 주입하는 반면, @Bean은 메서드를 통해 빈을 생성하고 등록합니다.

	@Autowired 
	private UserService userService; // SchoolService와 UserService는 서비스 계층의 빈(Bean)으로 등록되어 있어야 하며, 스프링이 자동으로 주입해줌
									 // 덕분에 MainControl에서 별도로 객체를 생성할 필요가 없음
	
	
	// 스프링은 클라이언트의 요청을 매핑(연결)해주는 다양한 어노테이션을 제공
	// @GetMapping은 GET요청 , @PosrMapping은 POST요청 처리
	
	@GetMapping("/test") //  localhost/test와 같은 주소로 요청이 들어온다면 동작하겠다(메서드 실행)
	public String testPage() { // 메서드이름은 아무거나
		System.out.println("와 진짜 실행 된다 .... 한글 좋아.....");
		return "hello.html"; // 보여줄 페이지 경로를 리턴시킴
	}
	
	// 로그인 화면
	@GetMapping("/signIn")
	public String login() {
		return "signIn";
	}
	@PostMapping("/signIn") //login오버로딩한것
	public String login(@RequestParam("id") String id,@RequestParam("pw") String pw, HttpSession session) { // @RequestParam : 클라이언트가 보낸 폼 데이터를 받아옵니다(id,pw) 
//		URL에 데이터를 담아서 보낼때는 @RequestParam사용 / HttpSession : 로그인 상태 유지를 위해 세션 객체를 사용 / userService.loginChk(id,pw) : 서비스 계층에 아이디와 비밀번호 검증을 요청하고, 결과에 따라 페이지를 다르게 반환
		boolean isSuccess = userService.loginChk(id,pw);
		if(isSuccess) {
			session.setAttribute("user",id);
			
			return "index";
		}
		
		return "index";
	}
	// 회원가입 화면
	@GetMapping("/signUp")
	public String join() {
		return "signUp";
	}
	@PostMapping("/signUp")
	// 파라미터가 많을 때 쉽게하는 법 > input태그의 name과 클래스의 변수의 이름이 동일해야함. 
	public String joinSave(@ModelAttribute User user) {// @ModelAttribute : 폼에서 넘어온 데이터를 User객체에 매핑 / input태그의 name속성과 User클래스의 변수명이 일치해야 자동으로 매핑 > 이후 userService.save(user)메서드를 호출해 회원정보를 저장 > 하나씩get꺼내오고 클래스객체만들고 set으로 다 저장했는데 이걸 다 해줌
		// 파라미터값이 DTO의 User객체에 저장됨 @ModelAttribute를 사용하려면 user클래스의 객체를 만들고 set메서드 호출해서 파라미터값을 넣어주는 역할 > 얘 쓰려면 항목개수와 이름모두 일치해야함 >근데 id는 없쥬?항목개수가 안맞아~~signUp.jsp에 input태그로 id만들어줘야함 타입을hidden으로하면 화면에는 표시는 안되나 자리는 차지
		System.out.println(user.getUserId());
		
		userService.save(user);
		
		return "index"; // 회원가입 저장하고 첫페이지 돌려보내기
	}
	//정보 수정 화면
	@GetMapping("/userUpdate") // ModelAndView : 뷰와 데이터를 함께 전달
	public ModelAndView memberUpdate(HttpSession session) { // 로그인정보를 session에 저장하도록 해놨으니 이걸 가져오로면 session가져오기
		
		ModelAndView mav = new ModelAndView("memberModify"); // /userUpdate요청 들어오면 보여줄 페이지
		
		// 현재 로그인한 회원의 정보를 가져와서 페이지에 출력하기
		String id = (String)session.getAttribute("user");  // 로그인한 아이디
		User info = userService.userDetail(id); // 회원정보조회가져오기
		mav.addObject("info",info); // 회원정보 모델앤뷰에 저장해야 뷰에 출력 > info객체를 "info"라는 이름으로 뷰에 전달
		
		return mav;
	}
	
	
	@PostMapping("/userUpdate")
	public String userUpdate(@RequestParam Map<String, String> param) { 
		//input 태그의 name이 key, input태그에 작성한 내용이 value
		// map에 각각 저장된다.
		System.out.println(param.get("id"));
		
		userService.update( param );
		
		return "index";
	}
	
	
	@GetMapping("/logout") // 세션에서 사용자 정보를 제거해 로그아웃을 처리하고, 이후 index페이지로 리다이렉트함
	public String logout(HttpSession session) { // 로그인 유지 위해 session이용한것임
		session.removeAttribute("user");
		return "index"; // 다시 첫페이지로 돌려보냄
	}
//	
//	
//	@GetMapping("/signIn")
//	public String viewPage() {
//		System.out.println("뷰페이지 성공");
//		return "signIn.html";
//	}
	
	@GetMapping("/") // localhost만 입력해도 index.html이 뜨게
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/list")// 클라이언트가 /list?type=유치원 과 같은 요청을 보내면 type값을 받아옴 
						// 유치원클릭했을때 파라미터 "유"를 가져옴.
	public ModelAndView listPage(@RequestParam String type) {
		System.out.println(type);
		ModelAndView mav = new ModelAndView("list");// 페이지제공과 데이터도 같이 제공
		//mav.setViewName(type);
		
		// 클라이언트가 요청한 유치원, 초등학교, 중학교, 고등학교에 대해 조회하기 위해
		// service클래스 객체에 넘겨주고 필요한 데이터를 받아서 뷰페이지와 함께 클라이언트에게 전달
		
		List<School> list = schoolService.typeSelect(type); // type에 따라 적절한 학교 목록을 가져옴
		mav.addObject("list",list); // ModelAndView에 저장 > 그래야 표시시킬수있음 내가정해준이름"list"에 위의 list값을 저장하겠다?? list는 list.html이라 확장자가 html인것에 자바를 쓸수없음
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
 		
 		< 전체 구조 정리 >
		MainControl.java: 컨트롤러 역할. 요청 처리 및 서비스 호출.	
		UserService.java, SchoolService.java: 비즈니스 로직 담당.		
		User.java, School.java: 데이터 모델 (DTO).		
		signIn.html, signUp.html, index.html: 클라이언트에게 보여질 뷰.
 */