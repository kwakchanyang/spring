package com.bookSystem.DTO;

import com.bookSystem.Entity.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookWriteDto {
	private String bookTitle;
	private String bookAuthor;
	private String bookPublishing;
	private int bookYear;
	
	public static BookWriteDto of(Book book) { // 이메서드는 Book엔티티 > BookWriteDto로 변환 / DB에서 가져온 Book데이터를 가공해서 DTO로 만들어주는 역할
		BookWriteDto bookWriteDto = new BookWriteDto();
		bookWriteDto.setBookAuthor(book.getBook_author());
		bookWriteDto.setBookPublishing(book.getBook_publishing());
		bookWriteDto.setBookTitle(book.getBook_title());
		bookWriteDto.setBookYear(book.getBook_year());
		
		return bookWriteDto;
	}
}


//DTO에서 사용자가 입력한 데이터를 받아오고(페이지)
// Entity에 넘겨서 데이터베이스 저장

// 데이터베이스에서 가져와서 Entity에 저장하고
// DTO에 넘겨서 뷰페이지 출력

// ----------- 왜 DTO를 만들까? ------------------------
// 1. 필요한 정보만 주고받아 불피리요한 정보의 전달을 막음(불필요한 데이터 숨겨짐)
// 책ID,제목,저자,출판사,출판연도,생성일,수정일 중에서 필요한것만 프론트로 전달
// 2. BookWriteDto를 사용해 하나의 객체에 여러개의 값을 다룰 수 있음
// 예) public void bookSave(String bookTitle, String bookAuthor, String bookPublishing, int bookYear) {
// 	   }   -> 매개변수가 많아 관리 어려움
// 		public void bookSave(BookWriteDto bookWriteDto) {
// 		DTO 객체 하나만 전달하면 됨!
//		}
// 3. 데이터를 변환하거나 가공할 수 있음
//	public class BookWriteDto {
//		private int
//		public String getFormattedYear(){
//			return bookYear+"년 출판";
//		}
//	}  -> 엔티티는 건드리지 않고, DTO안에서만 데이터를 변환할 수 있음
