package com.talk.Repository;

import org.apache.ibatis.annotations.Mapper;

import com.talk.Entity.MemberEntity;

@Mapper
public interface MemberRepository {
	// 회원가입 정보테이블에 저장 메서드
	public int insert(MemberEntity memberEntity);
	// 로그인을 위한 쿼리동작 메서드
	public boolean findByMemberIdAndPassword(MemberEntity memberEntity);
}
