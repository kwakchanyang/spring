package com.bookSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter // th등등에서 변수사용할때 자동으로 get,set생성해서 값이 저장되는 것임
@Setter
public class BookSearchDto {
	private String keyword; // 검색어
	private String option; // 검색 옵션 ( 제목 )
}
