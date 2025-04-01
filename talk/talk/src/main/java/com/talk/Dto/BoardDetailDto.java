package com.talk.Dto;

import java.time.LocalDate;
import java.util.List;

import com.talk.Entity.BoardEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDetailDto {
	private int id;
	private String title;
	private String memberId;
	private String content;
	private String fileName;
	private int hit;
	private LocalDate writeDate;
	private List<CommentViewDto> commentList;
	// 얘가 왜 List인가? 한개의 게시글(Board)에 여러개의 댓글(Comment)이 달릴수 있기 때문
	// list로 댓글 목록을 조회한다 // 댓글 Entity > DTO변환 > List<CommentViewDto>
	
	public static BoardDetailDto of(BoardEntity boardEntity, List<CommentViewDto> commentViewDtos) {
		BoardDetailDto boardDetailDto = new BoardDetailDto();
		boardDetailDto.setId(boardEntity.getId());
		boardDetailDto.setTitle(boardEntity.getTitle());
		boardDetailDto.setMemberId(boardEntity.getMemberId());
		boardDetailDto.setContent(boardEntity.getContent());
		boardDetailDto.setFileName(boardEntity.getFileName());
		boardDetailDto.setHit(boardEntity.getHit());
		boardDetailDto.setWriteDate(boardEntity.getWriteDate());
		boardDetailDto.setCommentList(commentViewDtos);
		return boardDetailDto;
	}
}
