package com.springStudy1.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springStudy1.DTO.School;

@Repository // 데이터베이스의 레퍼지토리 역할을 하겠다.
public class SchoolDAO { 
	private final JdbcTemplate jdbc; // final붙은것은 무조건 초기화해주기(초기화블록,명시적초기화,생성자메서드)
	
	@Autowired // 자동으로 의존성주입시켜줌 > 자동으로 의존성주입이 되도록 등록시켜줘야함
	// 의존성 주입 : 객체가 필요로 하는 다른 객체를 외부에서 주입해 주는 방식
	// 자동 주입 : @Autowired를 사용하면 스프링이 자동으로 해당 필드나 생성자에 필요한 빈(Bean)을 찾아서 주입 > 개발자가 의존성을 수동으로 관리할 필요 x
	public SchoolDAO( JdbcTemplate jdbc) {
		this.jdbc = jdbc; // 약한 결합(메서드통해 매개변수받아오는방식) jdbc객체만드려면 드라이버로드하고 접속(로그인)해야 만들어짐 > jsp에서는 내가 언제이걸하겠다고 정했는데 스프링에서는 스프링이 알아서 하게하기
	}
	
	//정보 가져오기(여러개의 정보를 넘겨야 하니까 컬렉션(ArrayList)사용
	public List<School> findAllByType(String type){ // 이 리스트에 School 객체만 저장하겠다(이 클래스의 인스턴스들로만 구성 > 다른 타입의 객체가 잘못들어가는것 방지), type매개변수는 특정조건에 맞는 데이터를 조회하기 위한 필터 역할 > 이 type을 schoolservice에 넘기고 dao에 넘겨준
		
		List<School> list = null;
		
		// list = jdbc.query("쿼리문", new SchoolRowMapper() ); RowMapper은 있지만 SchoolRowMapper클래스는 존재 x > 내가 클래스 만들어줘야함
		String sql = "select * from school where diff=?"; // diff는 school테이블에서 특정열을 나타냄 > 이 열의 값이 type매개변수와 일치하는 레코드만 조회 / ?는 PreparedStatement의 자리 표시자(placeholder)로, 실행 시 실제 값으로 대체
														  // 예를 들어, type이 고등학교라면 고등학교에 해당하는 데이터만 조회
		//JdbcTemplate의 query 메서드를 사용할 때 RowMapper를 지정하여 쿼리 결과를 객체 리스트로 변환
		list = jdbc.query(sql, new SchoolRowMapper(), type); // 얘 호출하면 type매개변수가 sql쿼리의 ?자리에 자동으로 바인딩됨
		
		// JdbcTemplate은 Connection, PreparedStatement, ResultSet이
		// 간단하게 사용해서 데이터베이스 이용할 수 있게 메서드로 만들어져 있다.
		// 여러 데이터를 조회하는 경우 query 메서드
		// 단일 레코드 조회하는 경우 queryForObject 메서드
		// 데이터 수정하는 경우 update 메서드
		// 삭제하는 경우 update 메서드
		// 새로운 데이터 추가하는 경우 update 메서드
		
		return list;
	}
	
	public class SchoolRowMapper implements RowMapper<School>{

		@Override
		public School mapRow(ResultSet rs, int rowNum) throws SQLException { //ResultSet rs:sql쿼리결과를 나타내는 ResultSet객체 / int rowNum:현재행의 인덱스(0부터시작) / School:반환되는 객체의 타입
			School school = new School();
			school.setId(rs.getInt("id")); // ""안에는 데이터베이스 컬럼명 쓰기
			school.setAddress(rs.getString("address"));
			school.setHomePage(rs.getString("homepage"));
			school.setSchoolName(rs.getString("school_name"));
			school.setTel(rs.getString("tel"));
			
			return school;
		}
		
	}
	
}
