package com.springStudy1.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // 데이터베이스의 레퍼지토리 역할을 하겠다.
public class SchoolDAO { 
	private final JdbcTemplate jdbc; // final붙은것은 무조건 초기화해주기(초기화블록,명시적초기화,생성자메서드)
	
	@Autowired // 자동주입시켜줌 > 자동으로 의존성주입이 되도록 등록시켜줘야함
	public SchoolDAO( JdbcTemplate jdbc) {
		this.jdbc = jdbc; // 약한 결합(메서드통해 매개변수받아오는방식) jdbc객체만드려면 드라이버로드하고 접속(로그인)해야 만들어짐 > jsp에서는 내가 언제이걸하겠다고 정했는데 스프링에서는 스프링이 알아서 하게하기
	}
}
