package com.bookSystem.DTO;

import java.time.LocalDate;

import com.bookSystem.Entity.BookReturn;
import com.bookSystem.Entity.BookUse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookReturnDto {
	private int id;
	private int member_id;
	private int book_id;
	private String status;
	private LocalDate loan_date;
	private LocalDate return_date;
	
	
	public static BookReturnDto of(BookUse bookUse) {
		
		BookReturnDto bookReturnDto = new BookReturnDto();
		bookReturnDto.setId(bookUse.getId());
		bookReturnDto.setMember_id(bookUse.getMember_id());
		bookReturnDto.setBook_id(bookUse.getBook_id());
		bookReturnDto.setStatus(bookUse.getStatus());
		bookReturnDto.setLoan_date(bookUse.getLoan_date());
		bookReturnDto.setReturn_date(bookUse.getReturn_date());
		
		return bookReturnDto;
	}
}
