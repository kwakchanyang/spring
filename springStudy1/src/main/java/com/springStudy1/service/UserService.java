package com.springStudy1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springStudy1.DAO.UserDao;
import com.springStudy1.DTO.User;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	private final UserDao userDao;
	
	@Autowired
	public UserService( UserDao userDao) {
		this.userDao = userDao;
	}
	public void save(User user) {
		// UserDao 객체의 메서드를 호출하여 테이블에 저장하기
		// 아이디 중복되지않게 저장
		boolean hasId = userDao.findByUserId( user.getUserId() );
		if( !hasId ) { // hasId가 거짓이라면 중복되지 않은 아이디
			userDao.insert(user);
		}
		
		
		
	}
	public boolean loginChk(String id, String pw, HttpSession session) { // 매개변수를 통해 session 객체 가져오기
		
		return userDao.findByUserIdAndUserPw(id,pw);//true,false값이 MainController의 boolean isSuccess까지 도달함
		
	
	}

}
