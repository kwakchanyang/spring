package com.talk.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private int boardId;
	private String content;
	private String memberId;
	
	// 왜 얘는 메서드가 없냐? 
	// 댓글 저장 기능만 하면 되니까 (사용자가 댓글을 입력하면 서버에서 DTO를 받아서 Entity로 변환후 DB에 저장하는 역할만 함)
}
