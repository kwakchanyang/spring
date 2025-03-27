package com.bookSystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookSystem.DTO.BookListDto;
import com.bookSystem.DTO.BookSearchDto;
import com.bookSystem.DTO.BookWriteDto;
import com.bookSystem.Entity.Book;
import com.bookSystem.Repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public void bookSave(BookWriteDto bookWriteDto) {
		Book book = Book.of(bookWriteDto); // BookWriteDto를 받아서 Book 객체로 변환 후 DB 저장
		bookRepository.save(book);
	}
	
	public List<BookListDto> bookSearch(BookSearchDto bookSearchDto){ // BookSearchDto를 받아서 조건에 맞는 책 목록을 가져옴
		
		List<BookListDto> bookListDtos = new ArrayList<>();
		
		List<Book> books = bookRepository.findByAll(bookSearchDto);
		
		for(Book book : books) {
			BookListDto bookListDto = new BookListDto(
					book.getBookId(), book.getBookTitle(),
					book.getBookAuthor(), book.getBookPublishing()
					);
			bookListDtos.add(bookListDto);
					
		}
		
		return bookListDtos;
		
	}
}
