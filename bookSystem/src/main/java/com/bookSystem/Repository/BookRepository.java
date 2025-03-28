package com.bookSystem.Repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bookSystem.DTO.BookSearchDto;
import com.bookSystem.Entity.Book;
import com.bookSystem.Entity.MyBasket;

@Mapper
public interface BookRepository {
	// 책 등록 - 테이블에 저장
	public int save(Book book); // Book객체를 받아서 DB에 저장
	
	// 책 검색 (조회)
	public List<Book> findByAll(BookSearchDto bookSearchDto );
	
	// 책 장바구니에 넣기 - 대여 하고싶은 도서 찜!!!
	// 맵 key : member_id -> mid , book_id -> bid 내가정할것임
	public int basketSave(Map<String, Integer> my);
	
	// 대출 메뉴 클릭시 장바구니 테이블에 있는 도서목록 가져오기
	public List<MyBasket> selectBasket(int memberId);
	
	// 대출 목록에 책 정보를 출력해야 하므로 mybasket테이블에 있는
	// book_id를 통해 책 정보 조회하기 하나씩 가져올꺼야!!
	public Book findById(int bookId);

	public void deleteBasket(int id);

	public void loanInsert(Map<String, Integer> info);
	
	// 반납
	
}
