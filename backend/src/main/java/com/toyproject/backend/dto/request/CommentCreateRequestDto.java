package com.toyproject.backend.dto.request;

import com.toyproject.backend.domain.Article;
import com.toyproject.backend.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequestDto {
	private String content;
	private Long articleNo;
	public Comment toEntity(Article article) {
		return Comment.builder()
			.content(content)
			.article(article)
			.build();
	}
}
