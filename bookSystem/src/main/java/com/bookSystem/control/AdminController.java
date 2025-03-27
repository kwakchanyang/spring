package com.bookSystem.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookSystem.DTO.BookWriteDto;
import com.bookSystem.Service.BookService;

@Controller
@RequestMapping("/admin") // 이미 여기서 admin주소 받았으니 밑에서 나머지 받음
public class AdminController { 	// localhost/admin/*** 으로 되어있는 모든주소 매핑
	@Autowired
	private BookService bookService;
	
	@GetMapping("/bookEnroll")	// localhost/admin/bookEnroll 주소 매핑
	public String enrollPage(Model model) { // 도서등록 페이지 제공
		
		model.addAttribute("bookWriteDto", new BookWriteDto());
		
		return "book/bookWrite";
	}
	
	@GetMapping("/bookSave") // 도서 테이블에 저장 요청
	public String saveBook(BookWriteDto bookWriteDto) {
		bookService.bookSave(bookWriteDto);
		
		return "redirect:/";
	}
}
