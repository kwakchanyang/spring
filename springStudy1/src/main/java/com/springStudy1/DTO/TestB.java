package com.springStudy1.DTO;

public class TestB {
	private int num;
	private String name;
	
	public TestB() {}
	public TestB(int num, String name) {
		this.num=num; // 매개변수로 받은 num(오른쪽)을 객체의 멤버변수 num(왼쪽)에 넣는것
		this.name=name;
	}
	public TestB(int num) {
		this.num=num;
	}
}
