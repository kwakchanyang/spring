package com.talk.Repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.talk.Entity.BoardEntity;

@Mapper
public interface BoardRepository {
	//게시글 저장 메서드
	public int insert(BoardEntity boardEntity);
	// 게시글 상세보기 메서드
	public BoardEntity findById(int id);
	// 게시글 삭제 메서드
	public int delete(int id);
	// 게시글 수정 메서드
	public int update(BoardEntity boardEntity);
	// 1페이지에 보여줄 게시글 목록(지정된 개수만큼 가져오기)
	public List<BoardEntity> findByIdBetween(Map<String,Integer> map);
	// 전체 게시글 개수 구하기 메서드
	public int findByAllCount();
	//최근글 가져오기 메서드(5개)
	public List<BoardEntity> findByOrderByWriteDateDesc();
	// 인기글 가져오기 메서드(5개)
	public List<BoardEntity> findByOrderByHitDesc();

}
