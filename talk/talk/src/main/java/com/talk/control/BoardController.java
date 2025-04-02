package com.talk.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.talk.Dto.BoardDto;
import com.talk.Dto.CommentDto;
import com.talk.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 게시판 목록 페이지 요청
	@GetMapping("/index")
	public String boardIndex(@RequestParam("id") int pageNum, Model model) {
		return null;
	}
	// 게시판 쓰기 페이지 요청
	@GetMapping("/write")
	public String boardWrite(HttpSession session, Model model) {
		return null;
	}
	// 게시판 저장 요청
	@GetMapping("/writeSave")
	public String boardWrite(BoardDto boardDto, HttpSession session, Model model) {
		return null;
	}
	//게시판 삭제 요청
	@GetMapping("/delete")
	public String boardDelete(@RequestParam("id") int id, HttpSession session) {
		return null;
	}
	// 게시판 수정 요청
	@GetMapping("/update")
	public String boardUpdate(BoardDto boardDto, HttpSession session) {
		return null;
	}
	// 게시글 상세 요청
	@GetMapping("/detail")
	public String boardDetail(@RequestParam("id") int id, HttpSession session, Model model) {
		return null;
	}
	// 게시글 수정 페이지 요청
	@GetMapping("/updatePage") 
	public String boardUpdate(@RequestParam("id") int id, HttpSession session, Model model) {
		return null;
	}
	// 댓글 저장 요청
	@GetMapping("/commentSave")
	public String commentSave(CommentDto commentDto, HttpSession session) {
		return null;
	}
	// 댓글 삭제 요청
	@GetMapping("/commentDelete")
	public String commentDel(@RequestParam("id") int id, HttpSession session) {
		return null;
	}
	
}
