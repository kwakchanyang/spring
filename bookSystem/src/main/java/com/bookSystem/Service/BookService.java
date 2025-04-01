package com.bookSystem.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bookSystem.DTO.BookBasketDto;
import com.bookSystem.DTO.BookListDto;
import com.bookSystem.DTO.BookLoanDto;
import com.bookSystem.DTO.BookSearchDto;
import com.bookSystem.DTO.BookWriteDto;
import com.bookSystem.Entity.Book;
import com.bookSystem.Entity.BookUse;
import com.bookSystem.Entity.MyBasket;
import com.bookSystem.Repository.BookRepository;
import com.bookSystem.Repository.MemberRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void bookSave(BookWriteDto bookWriteDto) {
		Book book = Book.of(bookWriteDto); // BookWriteDto를 받아서 Book 객체로 변환 후 DB 저장
		bookRepository.save(book);
	}
	
	public List<BookListDto> bookSearch(BookSearchDto bookSearchDto){ // BookSearchDto를 받아서 조건에 맞는 책 목록을 가져옴
		
		List<BookListDto> bookListDtos = new ArrayList<>();
		
		List<Book> books = bookRepository.findByAll(bookSearchDto);
		
		for(Book book : books) {
			BookListDto bookListDto = new BookListDto(
					book.getBook_id(), book.getBook_title(),
					book.getBook_author(), book.getBook_publishing()
					);
			bookListDtos.add(bookListDto);
					
		}
		
		return bookListDtos;
		
	}
	
	// 도서클릭하여 내 장바구니에 넣기
	// 클릭한 도서의 bookid 값 받아야 되고 , 현재 로그인한 회원 id(번호)가 필요 > mybasket테이블을 채우기 위함
	public void myBasketSave(int bookId, String email) {
		
		
		Map<String, Integer > my = new HashMap<>();
		my.put("mid", memberRepository.findByEmail(email));
		my.put("bid", bookId);
		
		bookRepository.basketSave( my );
	}
	
	// 대출 메뉴 페이지에 내장바구니 도서목록 띄우기
	// 현재 로그인한 회원의 장바구니 목록 띄우기 -> 컨트롤로부터 로그인 정보가져오기
	// 세션에 이메일 저장되어 있다.
	public List<BookBasketDto> myBasketList(String email){
		// 현재 로그인한 회원의 이메일을 통해 id 가져오기
		int memberId = memberRepository.findByEmail(email);
		
		// 회원 id와 일치하는 모든 데이터 조회( mybasket 테이블에서)
		List<MyBasket> myBasketList = bookRepository.selectBasket(memberId);
		
		// 현재 로그인한 회원의 mybasket에 있는 도서 번호(book_id)를 통해 도서정보가져오기
		List<BookBasketDto> bookBasketDtos = new ArrayList<>();
		// 가지고 와서 MyBasket과 Book객체의 데이터를 BookBasketDto의 객체로 저장되게 만들기
		List<BookBasketDto> basketDtos = new ArrayList<>();
		
		for(MyBasket temp : myBasketList) {
			Book book = bookRepository.findById(temp.getBook_id());
			BookBasketDto basketDto = BookBasketDto.of(temp, book); // of통해 객체만들코드 이미 있음
			
			bookBasketDtos.add(basketDto);
		}
		return bookBasketDtos;
	}
	
	// 대여 메뉴 클릭하면 
	public boolean loanSave(int id, int bookId, String email) {
		boolean hasLoan = bookRepository.loanCheck(bookId);
		if( hasLoan) return true;
			bookRepository.deleteBasket(id);
			
			Map<String, Integer> info = new HashMap<>();
			info.put("mid", memberRepository.findByEmail(email));
			info.put("bid", bookId);
			
			bookRepository.loanInsert( info );
		return false;
	}

	public List<BookLoanDto> myLoanList(String email) {
		
		List<BookLoanDto> list = new ArrayList<>();
		
		int memberId = memberRepository.findByEmail(email);
		
		List<BookUse> bookUses = bookRepository.findByMyLoan(memberId);
		for(BookUse bookUse : bookUses) {
			Book book = bookRepository.findById(bookUse.getBook_id());
			BookLoanDto bookLoanDto = BookLoanDto.of(bookUse, book);
			list.add(bookLoanDto);
		}		
		
		return list;
	}

	public void returnEx(int id) {
		
		bookRepository.returnUpdate(id);
		
	}
		
	}

