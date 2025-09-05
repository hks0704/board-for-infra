package com.toyproject.backend.dto.request;

import com.toyproject.backend.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateRequestDto {
	private Long articleNo;
	private String title;
	private String content;

	public Article toEntity() {
		return Article.builder()
			.title(title)
			.content(content)
			.build();
	}
}
