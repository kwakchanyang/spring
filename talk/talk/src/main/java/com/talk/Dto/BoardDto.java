package com.talk.Dto;

import com.talk.Entity.BoardEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	private int id;
	private String title;
	private String content;
	private String fileName;
	
	public static BoardEntity to(BoardDto boardDto) { // dto -> entity
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setId(boardDto.getId());
		boardEntity.setTitle(boardDto.getTitle());
		boardEntity.setContent(boardDto.getContent());
		boardEntity.setFileName(boardDto.getFileName());
		return boardEntity;
	}
	public static BoardDto from(BoardEntity boardEntity) { // entity -> dto
		BoardDto boardDto = new BoardDto();
		boardDto.setId(boardDto.getId());
		boardDto.setTitle(boardEntity.getTitle());
		boardDto.setContent(boardEntity.getContent());
		boardDto.setFileName(boardEntity.getFileName());
		return boardDto;
	}
}
