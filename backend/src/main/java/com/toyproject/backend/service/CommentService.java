package com.toyproject.backend.service;

import java.util.List;

import com.toyproject.backend.dto.request.CommentCreateRequestDto;
import com.toyproject.backend.dto.request.CommentUpdateRequestDto;
import com.toyproject.backend.dto.response.CommentResponseDto;

public interface CommentService {
	CommentResponseDto createComment(CommentCreateRequestDto requestDto);

	CommentResponseDto updateComment(CommentUpdateRequestDto requestDto);

	List<CommentResponseDto> findAllCommentsByArticleNo(Long articleNo);

	CommentResponseDto deleteComment(Long id);

}
