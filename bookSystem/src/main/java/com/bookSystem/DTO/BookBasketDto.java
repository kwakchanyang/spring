package com.bookSystem.DTO;

import com.bookSystem.Entity.Book;
import com.bookSystem.Entity.MyBasket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBasketDto {
	private int id; // mybasket테이블의 id 컬럼값
	private int book_id; // mybasket테이블의 book_id 컬럼값
	private String book_title; // book테이블의 book_title컬럼값
	private String book_author;// book테이블의 book_author컬럼값
	
	public static BookBasketDto of(MyBasket myBasket, Book book) {
//		of는 여러곳에서 객체를 만들어야 할 때의 메서드이다. > BookService에서 of를 써서 객체만듦
		BookBasketDto bookBasketDto = new BookBasketDto();
		bookBasketDto.setBook_id( myBasket.getBook_id());
		bookBasketDto.setBook_author(book.getBook_author());
		bookBasketDto.setBook_title(book.getBook_title());
		bookBasketDto.setId(myBasket.getId());
		
		return bookBasketDto;
	}
}
