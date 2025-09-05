package com.toyproject.backend.dto.response;

import java.time.LocalDateTime;

import com.toyproject.backend.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ArticleResponseDto {
	private Long articleNo;
	private String title;
	private String content;
	private LocalDateTime regDate;
	private LocalDateTime modDate;

	public ArticleResponseDto(Article article) {
		this.articleNo = article.getArticleNo();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.regDate = article.getRegDate();
		if (article.getModDate() != null) {
			this.modDate = article.getModDate();
		} else {
			this.modDate = null;
		}
	}
}
