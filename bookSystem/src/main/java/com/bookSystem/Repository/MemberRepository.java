package com.bookSystem.Repository;

import org.apache.ibatis.annotations.Mapper;

import com.bookSystem.DTO.MemberDto;

@Mapper // 레퍼지토리 하나당 xml하나 만들어주기 -> xml에서 mapper로 연결해줘야함
public interface MemberRepository { // 추상메서드는 내용이없잖아 > 연결된 클래스가 필요함 > 스프링은 m

	public String login(MemberDto memberDto);


}
