package com.toyproject.backend.dto.response;

import java.time.LocalDateTime;

import com.toyproject.backend.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentResponseDto {
	private Long commentNo;
	private String content;
	private LocalDateTime regDate;
	private LocalDateTime modDate;

	public CommentResponseDto(Comment comment) {
		this.commentNo = comment.getCommentNo();
		this.content = comment.getContent();
		this.regDate = comment.getRegDate();
		if (comment.getModDate() != null) {
			this.modDate = comment.getModDate();
		} else {
			this.modDate = null;
		}
	}
}
