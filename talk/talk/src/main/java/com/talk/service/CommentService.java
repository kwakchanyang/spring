package com.talk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talk.Dto.BoardDto;
import com.talk.Dto.CommentDto;
import com.talk.Entity.BoardEntity;
import com.talk.Entity.CommentEntity;
import com.talk.Repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public void commentSave(CommentDto commentDto, String memberId) {
		
		commentDto.setMemberId(memberId);
		
		CommentEntity commentEntity = CommentEntity.from(commentDto);
		
		commentRepository.insert(commentEntity);
	}
	
	public void commentDelete(int id) {
		
	}
}
