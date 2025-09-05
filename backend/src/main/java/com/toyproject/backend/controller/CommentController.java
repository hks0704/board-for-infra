package com.toyproject.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.backend.domain.ResponseData;
import com.toyproject.backend.dto.request.CommentCreateRequestDto;
import com.toyproject.backend.dto.request.CommentUpdateRequestDto;
import com.toyproject.backend.dto.response.CommentResponseDto;
import com.toyproject.backend.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
	private final CommentService commentService;

	@PostMapping("")
	public ResponseEntity<?> createComment(@RequestBody CommentCreateRequestDto requestDto) {
		CommentResponseDto responseDto = commentService.createComment(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseData.success("댓글 생성 완료", responseDto));
	}

	@GetMapping("/{articleNo}")
	public ResponseEntity<?> findAllComments(@PathVariable Long articleNo) {
		List<CommentResponseDto> responseDto = commentService.findAllCommentsByArticleNo(articleNo);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(responseDto));
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateComment(@RequestBody CommentUpdateRequestDto requestDto) {
		CommentResponseDto responseDto = commentService.updateComment(requestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success("댓글 수정 완료", responseDto));
	}

	@DeleteMapping("/{commentNo}")
	public ResponseEntity<?> deleteComment(@PathVariable Long commentNo) {
		CommentResponseDto responseDto = commentService.deleteComment(commentNo);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(responseDto));
	}

}
