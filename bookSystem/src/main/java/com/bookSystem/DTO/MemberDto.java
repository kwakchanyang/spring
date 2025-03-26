package com.bookSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto { // DB설계가 나와야 클래스 설계가 가능
	private String email;
	private String password;
}
