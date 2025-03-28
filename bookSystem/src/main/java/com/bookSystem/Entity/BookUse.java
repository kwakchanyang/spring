package com.bookSystem.Entity;

import java.time.LocalDate;

import com.bookSystem.DTO.BookReturnDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUse {
	private int id;
	private int member_id;
	private int book_id;
	private String status;
	private LocalDate loan_date;
	private LocalDate return_date;
	
	public static BookUse of(BookReturnDto bookReturnDto) {
		BookUse bookUse = new BookUse();
		bookUse.setMember_id(bookReturnDto.getMember_id());
		bookUse.setBook_id(bookReturnDto.getBook_id());
		bookUse.setStatus(bookReturnDto.getStatus());
		bookUse.setLoan_date(bookReturnDto.getLoan_date());
		bookUse.setReturn_date(bookReturnDto.getReturn_date());
		
		return bookUse;
	}

}
