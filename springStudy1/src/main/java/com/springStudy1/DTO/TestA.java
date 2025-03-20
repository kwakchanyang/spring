package com.springStudy1.DTO;

public class TestA {
	
	private TestB tb;
	private TestC tc;
	
	public void makeObject2(TestC tc) {
		// 약한 결합
		this.tc = tc; // (의존성 주입)혼자서 일처리 못하니까 다른쪽 불러와서 처리하는 것 /외부에서 만들어진걸 내부로 가져오는 것(어떤 생성자메서드로 만들어진지 상관없이 그냥 가져오면됨 - 변경불필요)
	}
	
	public void makeObject1() {
		// 강한 결합(한쪽이 변경하면 다른쪽도 변경해야함 > TestA에서 int num받아오는 메서드 필요다하 > TestB도 생성해야함 > 직접객체를 생성해야함.
		this.tb = new TestB(); // 상황에따라서 빈객체가 필요하거나 TestB가 필요하기 때문에 객체를 생성

	}
	public void makeObject1(int num, String name) { // 매개변수가 있는 객체도 필요하다면 둘다 생성
		this.tb = new TestB(num,name);
	}
	public void makeObject1(int num) {
		this.tb = new TestB(num);
	}
}
