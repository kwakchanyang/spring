package com.springStudy1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springStudy1.DAO.SchoolDAO;
import com.springStudy1.DTO.School;

@Service // 이걸넣어줘야함(의존성주입 사용하려면)
public class SchoolService { // 컨트롤러가 일다하지 않음, Service가 주소와 파라미터(데이터)가 들어왔다 > 얘가 함 >
	//SchoolDAO를 필요로함(의존성 주입)
	private final SchoolDAO schoolDao; // final은 꼭 초기화가 되야함!(생성자메서드로 해줄거임)
	
	@Autowired // 자동 주입 - SchoolService 객체가 생성되면 SchoolDAO객체생성하여 주입 // 이거 안해주면 객체생성코드,등등 다짜야함.
	public SchoolService(SchoolDAO schoolDao ) {
		this.schoolDao = schoolDao;
	}
	
	public List<School> typeSelect(String type){
		return schoolDao.findAllByType(type);
		// 이 메서드의 역할은 controller 클래스가 유치원,초등학교,중학교 등의 요청이 들어올 경우
		// controller에 의해서 실행되어야 하는 메서드이다.
		// service클래스는 controller클래스의 클라이언트 요청처리를 실행하기 위한 클래스
		// DAO와 controller 사이에서 전달자의 역할을 하기도 한다.
		// controller에서 직접 DAO를 호출하는 방식을 쓰기도 하지만 service를 통해 받는걸 권장
	}
}

/*
	1. Servie의 객체 생성(Autowired)  <(의존성주입) 2. DAO의 객체 <(의존성주입) 3. Jdbc 객체


 */