package com.springStudy1.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springStudy1.DTO.User;

@Repository
public class UserDao {

	private final JdbcTemplate jdbc;
	
	@Autowired
	public UserDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public boolean findByUserId(String userId) { // 아이디 중복 확인(이 메서드를 호출할때, 찾고싶은 사용자 아이디를 매개변수로 받음)
		
		String sql = "select * from user where user_id=?"; // UserId가 ?(파라미터 바인딩)에 자동으로 매칭/ ?는 나중에 값을 채우겠다는 뜻
		
		try {//	┌id는 확인만하는변수	 ┌DB에서 결과를 조회할때 사용     ┌결과 타입을 String으로 변환해서 반환함 / userId는 쿼리의 ?자리에 바인딩돼서 실행됨
			String id = jdbc.queryForObject(sql, String.class,userId); // 만약 (sql, User.class, userId, email) 이라면 첫번째?에 userId,두번째?에 email가 들어가서 SELECT * FROM user WHERE user_id='kcy' AND email='kcy@email.com'이런식이 들어감
		}catch(EmptyResultDataAccessException e){ // catch부분이 실행 된다는 것은 조회결과가 없다는 뜻
			return false; // 아이디가 중복이 아니다.
		}
		
		return true; // 아이디가 중복이다.
	}

	public void insert(User user) { // 회원 정보를 테이블에 저장
		String sql = "insert into user(user_id, user_pw, user_email, user_job, user_addr, user_like) values(?,?,?,?,?,?)";
		
		jdbc.update(sql, user.getUserId(), user.getUserPw(), user.getUserEmail(),
				user.getUserJob(), user.getUserAddr(),user.getUserLike());
		
	}

	public boolean findByUserIdAndUserPw(String id, String pw) {
		
		String sql = "select user_id from user where user_id=? and user_pw=?";
		
		try {
			String userId = jdbc.queryForObject(sql, String.class,id, pw);
		}catch(EmptyResultDataAccessException e) {
			return false; // 아이디 또는 비밀번호가 틀려서 결과가 없다.
		}
		
		return true; // 로그인 성공
	
	}

	public User findById(String id) {
		
		String sql = "select * from user where user_id=?";
		
		User user = jdbc.queryForObject(sql, new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException{
				User user = new User();
				 user.setUserId(rs.getString("user_id"));
				 user.setUserEmail(rs.getString("user_email"));
				 user.setUserAddr(rs.getString("user_addr"));
				 user.setUserJob(rs.getString("user_job"));
				 user.setUserLike(rs.getString("user_like"));
				return user;
			}
		},id);
		
		return user;
	}

	public void update(User user) {
		
		String sql = "update user set user_pw=?, user_email=?, user_addr=?,"
				+ "user_job=?, user_like=? where user_id=?";
		jdbc.update(sql, user.getUserPw(), user.getUserEmail(),
				user.getUserAddr(), user.getUserJob(),  user.getUserLike(), 
				user.getUserId());
		
	}


	
}
