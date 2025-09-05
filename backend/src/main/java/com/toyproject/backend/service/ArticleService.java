package com.toyproject.backend.service;

import java.util.List;

import com.toyproject.backend.dto.request.ArticleCreateRequestDto;
import com.toyproject.backend.dto.request.ArticleUpdateRequestDto;
import com.toyproject.backend.dto.response.ArticleResponseDto;

public interface ArticleService {
	ArticleResponseDto createArticle(ArticleCreateRequestDto requestDto);

	ArticleResponseDto updateArticle(ArticleUpdateRequestDto requestDto);

	List<ArticleResponseDto> findAllArticles();

	List<ArticleResponseDto> filterArticlesByTitle(String title);

	ArticleResponseDto deleteArticle(Long id);

	ArticleResponseDto findArticleByArticleId(Long articleNo);
}
